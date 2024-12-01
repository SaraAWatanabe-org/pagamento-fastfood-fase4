package com.challenge.fastfood.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PaymentStatusEnumTest {

    @Test
    @DisplayName("Should return correct code for each enum value")
    void testEnumValues() {
        assertEquals(1, PaymentStatusEnum.PENDING.getCode());
        assertEquals(2, PaymentStatusEnum.APPROVED.getCode());
        assertEquals(3, PaymentStatusEnum.REJECTED.getCode());
        assertEquals(4, PaymentStatusEnum.CANCELLED.getCode());
        assertEquals(5, PaymentStatusEnum.REFUNDED.getCode());
    }

    @Test
    @DisplayName("Should return correct enum for valid code")
    void testToEnumWithValidCode() {
        assertEquals(PaymentStatusEnum.PENDING, PaymentStatusEnum.toEnum(1));
        assertEquals(PaymentStatusEnum.APPROVED, PaymentStatusEnum.toEnum(2));
        assertEquals(PaymentStatusEnum.REJECTED, PaymentStatusEnum.toEnum(3));
        assertEquals(PaymentStatusEnum.CANCELLED, PaymentStatusEnum.toEnum(4));
        assertEquals(PaymentStatusEnum.REFUNDED, PaymentStatusEnum.toEnum(5));
    }

    @Test
    @DisplayName("Should return null for null code")
    void testToEnumWithNullCode() {
        assertNull(PaymentStatusEnum.toEnum(null));
    }

    @Test
    @DisplayName("Should throw exception for invalid code")
    void testToEnumWithInvalidCode() {
        Exception exception = assertThrows(RuntimeException.class, () ->
                PaymentStatusEnum.toEnum(99)
        );
        assertEquals("Payment Status code invalid. Code: 99", exception.getMessage());
    }
}
