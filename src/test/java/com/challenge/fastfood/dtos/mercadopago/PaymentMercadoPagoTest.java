package com.challenge.fastfood.dtos.mercadopago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentMercadoPagoTest {

    private PaymentMercadoPago paymentMercadoPago;

    @BeforeEach
    void setUp() {
        paymentMercadoPago = new PaymentMercadoPago();
    }

    @Test
    void testNoArgsConstructor() {
        assertThat(paymentMercadoPago.getId()).isNull();
        assertThat(paymentMercadoPago.getLive_mode()).isNull();
        assertThat(paymentMercadoPago.getType()).isNull();
        assertThat(paymentMercadoPago.getDate_created()).isNull();
        assertThat(paymentMercadoPago.getUser_id()).isNull();
        assertThat(paymentMercadoPago.getApi_version()).isNull();
        assertThat(paymentMercadoPago.getAction()).isNull();
        assertThat(paymentMercadoPago.getData()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        OffsetDateTime now = OffsetDateTime.now();
        MercadoPagoWebhookDataDTO data = new MercadoPagoWebhookDataDTO();

        paymentMercadoPago.setId(1L);
        paymentMercadoPago.setLive_mode(true);
        paymentMercadoPago.setType("PAYMENT");
        paymentMercadoPago.setDate_created(now);
        paymentMercadoPago.setUser_id(123456L);
        paymentMercadoPago.setApi_version("v1");
        paymentMercadoPago.setAction("create");
        paymentMercadoPago.setData(data);

        assertThat(paymentMercadoPago.getId()).isEqualTo(1L);
        assertThat(paymentMercadoPago.getLive_mode()).isTrue();
        assertThat(paymentMercadoPago.getType()).isEqualTo("PAYMENT");
        assertThat(paymentMercadoPago.getDate_created()).isEqualTo(now);
        assertThat(paymentMercadoPago.getUser_id()).isEqualTo(123456L);
        assertThat(paymentMercadoPago.getApi_version()).isEqualTo("v1");
        assertThat(paymentMercadoPago.getAction()).isEqualTo("create");
        assertThat(paymentMercadoPago.getData()).isEqualTo(data);
    }

}
