package com.challenge.fastfood.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.challenge.fastfood.models.PaymentModel;
import com.mercadopago.resources.payment.Payment;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

    @Mapping(source = "id", target = "transactionId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "pointOfInteraction.transactionData.qrCode", target = "qrCode")
    @Mapping(source = "pointOfInteraction.transactionData.ticketUrl", target = "ticketUrl")
    @Mapping(target = "numberLunch", ignore = true)
    @Mapping(target = "paymentType", ignore = true)
    @Mapping(target = "value", ignore = true)
    @Mapping(target = "status", ignore = true)
    PaymentModel toPaymentModel(Payment payment);

}
