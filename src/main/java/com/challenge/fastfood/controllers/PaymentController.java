package com.challenge.fastfood.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.fastfood.dtos.PaymentCreateDto;
import com.challenge.fastfood.dtos.mercadopago.PaymentMercadoPago;
import com.challenge.fastfood.enums.PaymentProviderEnum;
import com.challenge.fastfood.models.PaymentModel;
import com.challenge.fastfood.services.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@Tag(name = "payment", description = "Payment Controller")
@RequiredArgsConstructor
public class PaymentController {
	
	private final PaymentService paymentService;
	
    @PostMapping
    @Operation(summary = "Create Payment", description = "Create a payment for the lunch")
    public ResponseEntity<PaymentModel> create(@RequestBody PaymentCreateDto paymentRequest) {
    	PaymentModel paymentModel = paymentService.processPayment(paymentRequest);
        return ResponseEntity.ok().body(paymentModel);
    }

    @PostMapping("/mercado-pago-webhook")
    @Operation(summary = "Mercado Pago Webhook", description = "Mecado Pago webhook to update payment status")
    public ResponseEntity<PaymentModel> webhook(@RequestBody PaymentMercadoPago paymentRequest) {
    	String transactionID = paymentRequest.getData().getId();
    	PaymentModel paymentModel =  paymentService.checkPaymentStatus(transactionID, PaymentProviderEnum.MERCADO_PAGO);
    	return ResponseEntity.ok(paymentModel);
    }
    

}
