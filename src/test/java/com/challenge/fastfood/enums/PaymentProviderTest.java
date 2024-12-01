package com.challenge.fastfood.enums;

import com.challenge.fastfood.exceptions.DataIntegrityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PaymentProviderTest {

    @Test
    @DisplayName("Should return correct code for each enum value")
    void testEnumValues() {
        assertEquals("MERCADO_PAGO", PaymentProviderEnum.MERCADO_PAGO.getCode());
        assertEquals("MOCK_PAYMENT", PaymentProviderEnum.MOCK_PAYMENT.getCode());
    }

    @Test
    @DisplayName("Should return correct enum for valid code")
    void testToEnumWithValidCode() {
        assertEquals(PaymentProviderEnum.MERCADO_PAGO, PaymentProviderEnum.toEnum("MERCADO_PAGO"));
        assertEquals(PaymentProviderEnum.MOCK_PAYMENT, PaymentProviderEnum.toEnum("MOCK_PAYMENT"));
    }

    @Test
    @DisplayName("Should be case insensitive when converting code to enum")
    void testToEnumCaseInsensitive() {
        assertEquals(PaymentProviderEnum.MERCADO_PAGO, PaymentProviderEnum.toEnum("mercado_pago"));
        assertEquals(PaymentProviderEnum.MOCK_PAYMENT, PaymentProviderEnum.toEnum("mock_payment"));
    }

    @Test
    @DisplayName("Should throw exception for invalid code")
    void testToEnumWithInvalidCode() {
        Exception exception = assertThrows(DataIntegrityException.class, () ->
                PaymentProviderEnum.toEnum("INVALID_CODE")
        );
        assertEquals("Tipo de pagamento não reconhecido: INVALID_CODE", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for null code")
    void testToEnumWithNullCode() {
        Exception exception = assertThrows(DataIntegrityException.class, () ->
                PaymentProviderEnum.toEnum(null)
        );
        assertEquals("Tipo de pagamento não reconhecido: null", exception.getMessage());
    }
}
