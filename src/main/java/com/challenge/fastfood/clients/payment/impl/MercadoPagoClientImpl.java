package com.challenge.fastfood.clients.payment.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.challenge.fastfood.clients.payment.PaymentExternalClient;
import com.challenge.fastfood.enums.PaymentProviderEnum;
import com.challenge.fastfood.enums.PaymentStatusEnum;
import com.challenge.fastfood.exceptions.PaymentCreateFailException;
import com.challenge.fastfood.exceptions.PaymentException;
import com.challenge.fastfood.mappers.PaymentMapper;
import com.challenge.fastfood.models.LunchModel;
import com.challenge.fastfood.models.PaymentModel;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.payment.PaymentStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@RequiredArgsConstructor
public class MercadoPagoClientImpl implements PaymentExternalClient {

	private final PaymentMapper paymentMapper;

	@Value("${dataprovider.payment.mercado-pago.access-token:}")
	private String accessToken;

	@Value("${dataprovider.payment.mercado-pago.default-payer-email:}")
	private String defaultPayerEmail;

	@Value("${api.url:}")
	private String apiUrl;


	public PaymentProviderEnum getProviderCode() {
		return PaymentProviderEnum.MERCADO_PAGO;
	}

	@Override
	public PaymentModel createPayment(LunchModel lunchModel)  {

		MercadoPagoConfig.setAccessToken(accessToken);
		MercadoPagoConfig.setLoggingLevel(Level.FINEST);

		PaymentClient paymentClient = new PaymentClient();

		Map<String, String> customHeaders = new HashMap<String, String>();
		customHeaders.put("x-idempotency-key", lunchModel.getId().toString());

		MPRequestOptions.builder()
		//.accessToken("custom_access_token")
		.connectionRequestTimeout(2000)
		.connectionTimeout(2000)
		.socketTimeout(2000)
		.customHeaders(customHeaders)
		.build();

		String payerEmail = null;

		if( lunchModel.getClientEmail() != null) {
			payerEmail = lunchModel.getClientEmail();
		} else {
			payerEmail = this.defaultPayerEmail;
		}


		String notificationUrl = null;


		if(this.apiUrl != null && !this.apiUrl.isBlank()) {
			notificationUrl = apiUrl + "/payments/mercado-pago-webhook";
		}

		log.info("notificationUrl: {}", notificationUrl);


		PaymentCreateRequest createRequest =
				PaymentCreateRequest.builder()
				.transactionAmount(lunchModel.getValue())
				.externalReference(lunchModel.getId().toString())
				.description("fiap-techfood")
				.installments(1)
				.paymentMethodId("pix")
				.notificationUrl(notificationUrl)
				.payer(PaymentPayerRequest.builder().email(payerEmail).build())
				.build();

		try {
			Payment paymentResponse = paymentClient.create(createRequest);
			if(PaymentStatus.PENDING.equals(paymentResponse.getStatus())) {
				PaymentModel paymentModel = this.paymentMapper.toPaymentModel(paymentResponse);
				paymentModel.setNumberLunch(lunchModel.getId());
				paymentModel.setValue(lunchModel.getValue());
				paymentModel.setStatus(PaymentStatusEnum.PENDING);
				return paymentModel;
			} else {
				throw new PaymentCreateFailException("Falha para criar pagamento");
			}

		}catch (MPException e) {
			log.error("Error generate payment {}", e.getMessage());
			log.error(e.getMessage());
			throw new PaymentCreateFailException("Falha para criar pagamento");
		} catch (MPApiException e) {
			log.error("Error generate payment {}", e.getApiResponse().getContent());
			log.error("Error map {}", e.getApiResponse().getHeaders());
			log.error("Error status code {}", e.getApiResponse().getStatusCode());
			throw new PaymentCreateFailException("Falha para criar pagamento");
		}
	}

	@Override
	public PaymentStatusEnum checkPaymentStatus(String transactionId) {
		MercadoPagoConfig.setAccessToken(accessToken);
		MercadoPagoConfig.setLoggingLevel(Level.WARNING);

		PaymentClient paymentClient = new PaymentClient();


		MPRequestOptions.builder()
		.connectionRequestTimeout(2000)
		.connectionTimeout(2000)
		.socketTimeout(2000)
		.build();


		try {
			Long paymentMpId = Long.parseLong(transactionId);
			Payment paymentResponse = paymentClient.get(paymentMpId);

			PaymentModel paymentDomain = this.paymentMapper.toPaymentModel(paymentResponse);
			return paymentDomain.getStatus();
		} catch (NumberFormatException e) {
			log.error("Error to get payment: Invalid id given. Id: " + transactionId);
			throw new PaymentException("Error to get payment. Invalid Long Id: " + transactionId);
		} catch (MPException | MPApiException e) {
			log.error("Error generate payment {}", e.getMessage());
			log.error(e.getMessage());
			throw new PaymentException("Error to get payment.");
		}
	}

}
