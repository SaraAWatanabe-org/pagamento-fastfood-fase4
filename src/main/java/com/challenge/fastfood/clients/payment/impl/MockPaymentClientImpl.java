package com.challenge.fastfood.clients.payment.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.challenge.fastfood.clients.payment.PaymentExternalClient;
import com.challenge.fastfood.enums.PaymentProviderEnum;
import com.challenge.fastfood.enums.PaymentStatusEnum;
import com.challenge.fastfood.models.LunchModel;
import com.challenge.fastfood.models.PaymentModel;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MockPaymentClientImpl implements PaymentExternalClient {

	public PaymentProviderEnum getProviderCode() {
		return PaymentProviderEnum.MOCK_PAYMENT;
	}

	@Override
	public PaymentModel createPayment(LunchModel lunchModel)  {
		PaymentModel paymentDomain = new PaymentModel();

		paymentDomain.setId(UUID.randomUUID());
		paymentDomain.setStatus(PaymentStatusEnum.PENDING);
		paymentDomain.setQrCode("QR_CODE_FAKE_" + paymentDomain.getId());
		paymentDomain.setNumberLunch(lunchModel.getId());
		paymentDomain.setTicketUrl("FAKE_URL_"+ paymentDomain.getId());

		paymentDomain.setValue(lunchModel.getValue());
		paymentDomain.setTransactionId(paymentDomain.getId().toString());

		return paymentDomain;
	}

	@Override
	public PaymentStatusEnum checkPaymentStatus(String transactionId) {
		return PaymentStatusEnum.APPROVED;
	}

}
