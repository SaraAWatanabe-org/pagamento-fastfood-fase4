package com.challenge.fastfood.services.impl;

import com.challenge.fastfood.clients.payment.PaymentExternalClient;
import com.challenge.fastfood.clients.payment.PaymentFactory;
import com.challenge.fastfood.enums.LunchStatusEnum;
import com.challenge.fastfood.enums.PaymentProviderEnum;
import com.challenge.fastfood.enums.PaymentStatusEnum;
import com.challenge.fastfood.exceptions.DataIntegrityException;
import com.challenge.fastfood.exceptions.ObjectNotFoundException;
import com.challenge.fastfood.repositories.LunchRepository;
import com.challenge.fastfood.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.challenge.fastfood.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl service;
    @Mock
    PaymentRepository paymentRepository;
    @Mock
    PaymentFactory paymentFactory;
    @Mock
    LunchRepository lunchRepository;
    @Mock
    RestTemplate restTemplate;
    @Mock
    PaymentExternalClient paymentExternalClient;

    final Long LUNCH_ID = 1L;
    final String NEW_STATUS = "Pronto";
    final String TRANSACTION_ID = "123456";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(service, "lunchServiceUrl", "http://localhost:8081/lunches");
    }

    @Test
    @DisplayName("Should update Lunch Status")
    void updateLunchStatus() {
        assertDoesNotThrow(() -> service.updateLunchStatus(LUNCH_ID, NEW_STATUS));
    }

    @Test
    @DisplayName("Should throw ObjectNotFoundException when PaymentModel not found")
    void checkPaymentStatus_shouldThrowObjectNotFoundExceptionWhenPaymentModelNotFound() {
        when(paymentRepository.findPaymentByTransactionId(TRANSACTION_ID)).thenThrow(ObjectNotFoundException.class);

        assertThrows(ObjectNotFoundException.class, () -> service.checkPaymentStatus(TRANSACTION_ID, PaymentProviderEnum.MOCK_PAYMENT));
    }

    @Test
    @DisplayName("Should throw ObjectNotFoundException when LunchModel not found")
    void checkPaymentStatus_shouldThrowObjectNotFoundExceptionWhenLunchModelNotFound() {
        var paymentType = PaymentProviderEnum.MOCK_PAYMENT;
        var paymentStatus = PaymentStatusEnum.APPROVED;
        var paymentModel = getPaymentModelMock();

        when(paymentRepository.findPaymentByTransactionId(TRANSACTION_ID)).thenReturn(Optional.of(paymentModel));
        when(paymentFactory.getPaymentClient(paymentType)).thenReturn(paymentExternalClient);
        when(paymentExternalClient.checkPaymentStatus(TRANSACTION_ID)).thenReturn(paymentStatus);
        when(lunchRepository.findById(paymentModel.getNumberLunch())).thenThrow(ObjectNotFoundException.class);

        assertThrows(ObjectNotFoundException.class, () -> service.checkPaymentStatus(TRANSACTION_ID, paymentType));
    }

    @Test
    @DisplayName("Should process Approved payment status")
    void checkPaymentStatus_shouldProcessApprovedPaymentStatus() {
        var paymentType = PaymentProviderEnum.MOCK_PAYMENT;
        var paymentStatus = PaymentStatusEnum.APPROVED;
        var paymentModel = getPaymentModelMock();
        var lunchModel = getLunchModelMock();

        when(paymentRepository.findPaymentByTransactionId(TRANSACTION_ID)).thenReturn(Optional.of(paymentModel));
        when(paymentFactory.getPaymentClient(paymentType)).thenReturn(paymentExternalClient);
        when(paymentExternalClient.checkPaymentStatus(TRANSACTION_ID)).thenReturn(paymentStatus);
        when(lunchRepository.findById(paymentModel.getNumberLunch())).thenReturn(Optional.of(lunchModel));

        assertDoesNotThrow(() -> service.checkPaymentStatus(TRANSACTION_ID, paymentType));
    }

    @Test
    @DisplayName("Should any payment status")
    void checkPaymentStatus_shouldProcessAnyPaymentStatus() {
        var paymentType = PaymentProviderEnum.MOCK_PAYMENT;
        var paymentStatus = PaymentStatusEnum.PENDING;
        var paymentModel = getPaymentModelMock();
        var lunchModel = getLunchModelMock();

        when(paymentRepository.findPaymentByTransactionId(TRANSACTION_ID)).thenReturn(Optional.of(paymentModel));
        when(paymentFactory.getPaymentClient(paymentType)).thenReturn(paymentExternalClient);
        when(paymentExternalClient.checkPaymentStatus(TRANSACTION_ID)).thenReturn(paymentStatus);
        when(lunchRepository.findById(paymentModel.getNumberLunch())).thenReturn(Optional.of(lunchModel));

        assertDoesNotThrow(() -> service.checkPaymentStatus(TRANSACTION_ID, paymentType));
    }

    @Test
    @DisplayName("Should throw DataIntegrityException when lunchModel has found")
    void processPayment_shouldThrowDataIntegrityException() {
        var payment = getPaymentCreateDtoMock();
        var lunchModel = getLunchModelMock();
        lunchModel.setStatus(LunchStatusEnum.FINISHED);

        when(lunchRepository.findById(any())).thenReturn(Optional.of(lunchModel));
        when(lunchRepository.save(lunchModel)).thenReturn(lunchModel);

        assertThrows(DataIntegrityException.class, () -> service.processPayment(payment));
    }

    @Test
    @DisplayName("Should throw DataIntegrityException when lunchModel not found")
    void processPayment_shouldThrowDataIntegrityExceptionWhenLunchModelNotFound() {
        var payment = getPaymentCreateDtoMock();
        var lunchModel = getLunchModelMock();
        lunchModel.setStatus(LunchStatusEnum.FINISHED);

        when(lunchRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(DataIntegrityException.class, () -> service.processPayment(payment));
    }
    @Test
    @DisplayName("Should process payment")
    void processPayment_shouldProcessPayment() {
        var payment = getPaymentCreateDtoMock();
        var lunchModel = getLunchModelMock();

        when(lunchRepository.findById(any())).thenReturn(Optional.of(lunchModel));
        when(lunchRepository.save(lunchModel)).thenReturn(lunchModel);
        when(paymentFactory.getPaymentClient(any())).thenReturn(paymentExternalClient);
        when(paymentExternalClient.createPayment(any())).thenReturn(getPaymentModelMock());

        assertDoesNotThrow(() -> service.processPayment(payment));
    }


}
