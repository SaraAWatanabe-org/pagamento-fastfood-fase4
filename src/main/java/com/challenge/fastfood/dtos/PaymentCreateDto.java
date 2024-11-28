package com.challenge.fastfood.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentCreateDto {
	
    private Long id;
    private String status;
    private String cpf;
    
    @Email
    private String emailClient;
    
    @NotNull
    private Double priceTotal;
    
    @NotNull
    private Long numberLunch;
    private String transactionId;
    private String qrCode;
    private String ticketUrl;


}