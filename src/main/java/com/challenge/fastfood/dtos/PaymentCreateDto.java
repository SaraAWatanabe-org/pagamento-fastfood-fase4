package com.challenge.fastfood.dtos;

import java.math.BigDecimal;

import com.challenge.fastfood.enums.PaymentProviderEnum;

import lombok.Data;

@Data
public class PaymentCreateDto {
	
	private Long numberLunch; 
	private String name;
	private String clientEmail;
	private BigDecimal value;
	private String status;
	private PaymentProviderEnum paymentType;


}