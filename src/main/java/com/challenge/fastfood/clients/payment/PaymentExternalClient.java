package com.challenge.fastfood.clients.payment;

import com.challenge.fastfood.enums.PaymentProviderEnum;
import com.challenge.fastfood.enums.PaymentStatusEnum;
import com.challenge.fastfood.models.LunchModel;
import com.challenge.fastfood.models.PaymentModel;

public interface PaymentExternalClient {

	PaymentProviderEnum getProviderCode();

	PaymentModel createPayment(LunchModel lunchModel);

	PaymentStatusEnum checkPaymentStatus(String transactionId) ;

}
