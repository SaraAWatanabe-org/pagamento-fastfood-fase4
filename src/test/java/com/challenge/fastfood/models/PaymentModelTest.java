package com.challenge.fastfood.models;

import com.challenge.fastfood.enums.PaymentProviderEnum;
import com.challenge.fastfood.enums.PaymentStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PaymentModelTest {

    private PaymentModel paymentModel;

    @BeforeEach
    void setUp() {
        paymentModel = new PaymentModel();
        paymentModel.setId(UUID.randomUUID());
        paymentModel.setQrCode("https://example.com/qrcode");
        paymentModel.setTicketUrl("https://example.com/ticket");
        paymentModel.setNumberLunch(123L);
        paymentModel.setValue(new BigDecimal("49.99"));
        paymentModel.setStatus(PaymentStatusEnum.PENDING);
        paymentModel.setTransactionId("txn-12345");
        paymentModel.setPaymentType(PaymentProviderEnum.MOCK_PAYMENT);
    }

    @Test
    void testGettersAndSetters() {
        assertThat(paymentModel.getId()).isNotNull();
        assertThat(paymentModel.getQrCode()).isEqualTo("https://example.com/qrcode");
        assertThat(paymentModel.getTicketUrl()).isEqualTo("https://example.com/ticket");
        assertThat(paymentModel.getNumberLunch()).isEqualTo(123L);
        assertThat(paymentModel.getValue()).isEqualTo(new BigDecimal("49.99"));
        assertThat(paymentModel.getStatus()).isEqualTo(PaymentStatusEnum.PENDING);
        assertThat(paymentModel.getTransactionId()).isEqualTo("txn-12345");
        assertThat(paymentModel.getPaymentType()).isEqualTo(PaymentProviderEnum.MOCK_PAYMENT);
    }

    @Test
    void testSetStatusWithValidEnum() {
        paymentModel.setStatus(PaymentStatusEnum.APPROVED);
        assertThat(paymentModel.getStatus()).isEqualTo(PaymentStatusEnum.APPROVED);
    }

    @Test
    void testSetStatusWithNullEnum() {
        assertThrows(NullPointerException.class, () -> paymentModel.setStatus(null));
    }

    @Test
    void testSetPaymentTypeWithValidEnum() {
        paymentModel.setPaymentType(PaymentProviderEnum.MERCADO_PAGO);
        assertThat(paymentModel.getPaymentType()).isEqualTo(PaymentProviderEnum.MERCADO_PAGO);
    }

    @Test
    void testSetPaymentTypeWithNullEnum() {
        assertThrows(NullPointerException.class, () -> paymentModel.setPaymentType(null));
    }

    @Test
    void testSetQrCode() {
        paymentModel.setQrCode("https://example.com/new-qrcode");
        assertThat(paymentModel.getQrCode()).isEqualTo("https://example.com/new-qrcode");
    }

    @Test
    void testSetTicketUrl() {
        paymentModel.setTicketUrl("https://example.com/new-ticket");
        assertThat(paymentModel.getTicketUrl()).isEqualTo("https://example.com/new-ticket");
    }

}
