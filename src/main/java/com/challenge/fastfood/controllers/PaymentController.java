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

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class PaymentController {
	
	private PaymentService paymentService;
	
	
    @PostMapping("/create")
    public ResponseEntity<PaymentModel> create(@RequestBody PaymentCreateDto paymentRequest) {
    	PaymentModel paymentModel = paymentService.processPayment(paymentRequest);
        return ResponseEntity.ok().body(paymentModel);
    }

    @PostMapping("/mercado-pago-webhook")
    public ResponseEntity<PaymentModel> webhook(@RequestBody PaymentMercadoPago paymentRequest) {
    	String transactionID = paymentRequest.getData().getId();
    	PaymentModel paymentModel =  paymentService.checkPaymentStatus(transactionID, PaymentProviderEnum.MERCADO_PAGO);
    	return ResponseEntity.ok(paymentModel);
    }
    

}
