package com.challenge.fastfood.dtos.mercadopago;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PaymentMercadoPago {

    private Long id;
    private Boolean live_mode;
    private String type;
    private OffsetDateTime date_created;
    private Long user_id;
    private String api_version;
    private String action;
    private MercadoPagoWebhookDataDTO data;

}
