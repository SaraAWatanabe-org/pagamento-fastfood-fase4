package com.challenge.fastfood.controllers;

import com.challenge.fastfood.services.PaymentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.challenge.fastfood.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @InjectMocks
    PaymentController controller;
    @Mock
    PaymentService service;

    @Test
    @DisplayName("Should update Lunch Status")
    void updateLunchStatus() {
        var request = getPaymentCreateDtoMock();
        var response = getPaymentModelMock();

        when(service.processPayment(any())).thenReturn(response);

        assertDoesNotThrow(() -> controller.create(request));
    }

    @Test
    @DisplayName("Should throw ObjectNotFoundException when PaymentModel not found")
    void checkPaymentStatus_shouldThrowObjectNotFoundExceptionWhenPaymentModelNotFound() {
        var request = getPaymentMercadoPagoMock();
        var response = getPaymentModelMock();

        when(service.checkPaymentStatus(any(), any())).thenReturn(response);

        assertDoesNotThrow(() -> controller.webhook(request));
    }

}