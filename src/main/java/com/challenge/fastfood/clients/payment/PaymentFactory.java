package com.challenge.fastfood.clients.payment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.challenge.fastfood.enums.PaymentProviderEnum;

@Component
public class PaymentFactory {

	private final Map<PaymentProviderEnum, PaymentExternalClient> paymentClientMap;

	public PaymentFactory(List<PaymentExternalClient> paymentClientList) {
		this.paymentClientMap = paymentClientList.stream()
				.collect(Collectors.toMap(PaymentExternalClient::getProviderCode, paymentClient -> paymentClient));
	}

	public PaymentExternalClient getPaymentClient(PaymentProviderEnum provider) {
		return paymentClientMap.get(provider);
	}

}
