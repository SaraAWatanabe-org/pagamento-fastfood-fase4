package com.challenge.fastfood.dtos;

import lombok.Data;

@Data
public class PaymentCreateDto {
	
    private Long id;
    private String status;
    private String cpf;
    private String emailClient;
    private double priceTotal;
    private Long numberLunch;
    private String transactionId;
    private String qrCode;
    private String ticketUrl;


}