package com.challenge.fastfood.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.challenge.fastfood.clients.payment.PaymentExternalClient;
import com.challenge.fastfood.clients.payment.PaymentFactory;
import com.challenge.fastfood.dtos.PaymentCreateDto;
import com.challenge.fastfood.dtos.UpdateLunchStatusRequest;
import com.challenge.fastfood.enums.LunchStatusEnum;
import com.challenge.fastfood.enums.PaymentProviderEnum;
import com.challenge.fastfood.enums.PaymentStatusEnum;
import com.challenge.fastfood.exceptions.DataIntegrityException;
import com.challenge.fastfood.exceptions.ObjectNotFoundException;
import com.challenge.fastfood.models.LunchModel;
import com.challenge.fastfood.models.PaymentModel;
import com.challenge.fastfood.repositories.LunchRepository;
import com.challenge.fastfood.repositories.PaymentRepository;
import com.challenge.fastfood.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepository;

	private final PaymentFactory paymentFactory;

	private final LunchRepository lunchRepository;

	private final RestTemplate restTemplate;

	private final String lunchServiceUrl;

	public PaymentServiceImpl(
			PaymentRepository paymentRepository, 
			PaymentFactory paymentFactory,
			LunchRepository lunchRepository,
			RestTemplate restTemplate,
			@Value("${lunch.service.url}") String lunchServiceUrl) {
		super();
		this.paymentRepository = paymentRepository;
		this.paymentFactory = paymentFactory;
		this.lunchRepository = lunchRepository;
		this.restTemplate = restTemplate;
		this.lunchServiceUrl = lunchServiceUrl;
	}


	@Transactional
	public PaymentModel processPayment(PaymentCreateDto paymentCreateDto) {

		Optional<LunchModel> lunchModelOptional = lunchRepository.findById(paymentCreateDto.getNumberLunch());

		LunchModel lunchModel = null;

		if(lunchModelOptional.isEmpty()) {
			lunchModel = toLunchModel(paymentCreateDto);
		} else {
			lunchModel = lunchModelOptional.get();
			lunchModel = lunchRepository.save(lunchModel);
		}


		if(!(lunchModel.getStatus().equals(LunchStatusEnum.READY) 
				|| lunchModel.getStatus().equals(LunchStatusEnum.PAYMENT_CREATE_FAILED)
				|| lunchModel.getStatus().equals(LunchStatusEnum.PAYMENT_REJECTED))) {
			throw new DataIntegrityException("It is not possible to create a Payment for the order.");
		}

		PaymentExternalClient paymentClient = paymentFactory.getPaymentClient(paymentCreateDto.getPaymentType());

		if(paymentClient == null) {
			throw new DataIntegrityException("Não foi selecionado um meio de pagamento válido. Code: " + paymentCreateDto.getPaymentType());
		}

		PaymentModel paymentModel = paymentClient.createPayment(lunchModel);
		paymentModel.setPaymentType(paymentCreateDto.getPaymentType());
		paymentModel.setValue(paymentCreateDto.getValue());
		paymentModel.setNumberLunch(paymentCreateDto.getNumberLunch());
		paymentRepository.save(paymentModel);

		lunchModel.getPayments().add(paymentModel);
		lunchRepository.save(lunchModel);

		return paymentModel;
	}

	private LunchModel toLunchModel(PaymentCreateDto paymentCreateDto) {
		LunchModel lunchModel = new LunchModel();
		lunchModel.setId(paymentCreateDto.getNumberLunch());
		lunchModel.setName(paymentCreateDto.getName());
		lunchModel.setClientEmail(paymentCreateDto.getClientEmail());
		lunchModel.setValue(paymentCreateDto.getValue());
		lunchModel.setStatus(LunchStatusEnum.RECEIVED);
		return lunchModel;
	}

	public void updateLunchStatus(Long lunchId, int newStatus) {
		String url = String.format("%s/%s/status", lunchServiceUrl, lunchId);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");

		UpdateLunchStatusRequest request = new UpdateLunchStatusRequest(newStatus);

		HttpEntity<UpdateLunchStatusRequest> entity = new HttpEntity<>(request, headers);

		restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class, lunchId);
	}

	@Override
	public PaymentModel checkPaymentStatus(String transactionId, PaymentProviderEnum paymentType) {
		PaymentModel paymentModel = paymentRepository.findPaymentByTransactionId(transactionId)
				.orElseThrow(() -> new ObjectNotFoundException("Pagamento não encontrado. TransactionId: " + transactionId));


		PaymentExternalClient client = paymentFactory.getPaymentClient(paymentType);

		PaymentStatusEnum paymentStatus = client.checkPaymentStatus(transactionId);
		paymentModel.setStatus(paymentStatus);

		//TODO CHAMAR ENDPOINT MICROSERVIÇOS LUNCH
		//updateLunchStatus();

		return paymentRepository.save(paymentModel);
	}


}
