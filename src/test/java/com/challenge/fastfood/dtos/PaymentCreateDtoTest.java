package com.challenge.fastfood.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentCreateDtoTest {

    private PaymentCreateDto paymentCreateDto;

    @BeforeEach
    void setUp() {
        paymentCreateDto = new PaymentCreateDto();
    }

    @Test
    void testNoArgsConstructor() {
        assertThat(paymentCreateDto.getId()).isNull();
        assertThat(paymentCreateDto.getStatus()).isNull();
        assertThat(paymentCreateDto.getCpf()).isNull();
        assertThat(paymentCreateDto.getEmailClient()).isNull();
        assertThat(paymentCreateDto.getPriceTotal()).isNull();
        assertThat(paymentCreateDto.getNumberLunch()).isNull();
        assertThat(paymentCreateDto.getTransactionId()).isNull();
        assertThat(paymentCreateDto.getQrCode()).isNull();
        assertThat(paymentCreateDto.getTicketUrl()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        paymentCreateDto.setId(1L);
        paymentCreateDto.setStatus("COMPLETED");
        paymentCreateDto.setCpf("12345678900");
        paymentCreateDto.setEmailClient("client@example.com");
        paymentCreateDto.setPriceTotal(100.50);
        paymentCreateDto.setNumberLunch(10L);
        paymentCreateDto.setTransactionId("txn-12345");
        paymentCreateDto.setQrCode("https://example.com/qrcode");
        paymentCreateDto.setTicketUrl("https://example.com/ticket");

        assertThat(paymentCreateDto.getId()).isEqualTo(1L);
        assertThat(paymentCreateDto.getStatus()).isEqualTo("COMPLETED");
        assertThat(paymentCreateDto.getCpf()).isEqualTo("12345678900");
        assertThat(paymentCreateDto.getEmailClient()).isEqualTo("client@example.com");
        assertThat(paymentCreateDto.getPriceTotal()).isEqualTo(100.50);
        assertThat(paymentCreateDto.getNumberLunch()).isEqualTo(10L);
        assertThat(paymentCreateDto.getTransactionId()).isEqualTo("txn-12345");
        assertThat(paymentCreateDto.getQrCode()).isEqualTo("https://example.com/qrcode");
        assertThat(paymentCreateDto.getTicketUrl()).isEqualTo("https://example.com/ticket");
    }

}
