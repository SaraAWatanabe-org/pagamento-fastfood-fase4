package com.challenge.fastfood.utils;

import com.challenge.fastfood.clients.payment.PaymentExternalClient;
import com.challenge.fastfood.dtos.PaymentCreateDto;
import com.challenge.fastfood.dtos.mercadopago.PaymentMercadoPago;
import com.challenge.fastfood.enums.LunchStatusEnum;
import com.challenge.fastfood.models.LunchModel;
import com.challenge.fastfood.models.PaymentModel;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static PaymentModel getPaymentModelMock() {
        return EasyRandomUtils.nextObject(PaymentModel.class);
    }

    public static LunchModel getLunchModelMock() {
        var response = EasyRandomUtils.nextObject(LunchModel.class);
        var list = new ArrayList<PaymentModel>();
        list.add(getPaymentModelMock());
        response.setStatus(LunchStatusEnum.RECEIVED);
        response.setPayments(list);
        return response;
    }

    public static PaymentCreateDto getPaymentCreateDtoMock() {
        return EasyRandomUtils.nextObject(PaymentCreateDto.class);
    }

    public static PaymentMercadoPago getPaymentMercadoPagoMock() {
        return EasyRandomUtils.nextObject(PaymentMercadoPago.class);
    }

}
