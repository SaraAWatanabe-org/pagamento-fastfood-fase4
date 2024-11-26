package com.challenge.fastfood.models;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.challenge.fastfood.enums.PaymentProviderEnum;
import com.challenge.fastfood.enums.PaymentStatusEnum;

import lombok.Data;

@Data
@Document(collection = "payments")
public class PaymentModel {

	@Id
	private UUID id;
	private String qrCode;
	private String ticketUrl;

	private Long numberLunch;
	private BigDecimal value;
	private Integer status;
	private String transactionId;
	
	private String paymentType;


	public PaymentStatusEnum getStatus() {
		return PaymentStatusEnum.toEnum(this.status);
	}

	public void setStatus(PaymentStatusEnum status) {
		this.status = status.getCode();
	}
	
	public PaymentProviderEnum getPaymentType() {
		return PaymentProviderEnum.toEnum(this.paymentType);
	}

	public void setPaymentType(PaymentProviderEnum paymentType) {
		this.paymentType = paymentType.getCode();
	}

}
