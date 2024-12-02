package com.challenge.fastfood.dtos.mercadopago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MercadoPagoWebhookDataDTOTest {

    private MercadoPagoWebhookDataDTO webhookDataDTO;

    @BeforeEach
    void setUp() {
        webhookDataDTO = new MercadoPagoWebhookDataDTO();
    }

    @Test
    void testNoArgsConstructor() {
        assertThat(webhookDataDTO.getId()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        webhookDataDTO.setId("12345");

        assertThat(webhookDataDTO.getId()).isEqualTo("12345");
    }

}