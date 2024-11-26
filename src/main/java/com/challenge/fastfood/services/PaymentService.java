package com.challenge.fastfood.services;

import com.challenge.fastfood.dtos.PaymentCreateDto;
import com.challenge.fastfood.enums.PaymentProviderEnum;
import com.challenge.fastfood.models.PaymentModel;

public interface PaymentService {

	public PaymentModel processPayment(PaymentCreateDto paymentCreateDto);

	public PaymentModel checkPaymentStatus(String transactionId, PaymentProviderEnum paymentType);
}
