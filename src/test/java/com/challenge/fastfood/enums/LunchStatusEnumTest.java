package com.challenge.fastfood.enums;

import com.challenge.fastfood.exceptions.DataIntegrityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LunchStatusEnumTest {

    @Test
    @DisplayName("Should return correct code and description for each enum value")
    void testEnumValues() {
        assertEquals("RECEIVED", LunchStatusEnum.RECEIVED.getCode());
        assertEquals("Recebido", LunchStatusEnum.RECEIVED.getDescription());

        assertEquals("PAYMENT_APPROVED", LunchStatusEnum.PAYMENT_APPROVED.getCode());
        assertEquals("Pagamento Aprovado", LunchStatusEnum.PAYMENT_APPROVED.getDescription());

        assertEquals("PAYMENT_CREATE_FAILED", LunchStatusEnum.PAYMENT_CREATE_FAILED.getCode());
        assertEquals("Erro Criação Pagamento", LunchStatusEnum.PAYMENT_CREATE_FAILED.getDescription());

        assertEquals("PAYMENT_REJECTED", LunchStatusEnum.PAYMENT_REJECTED.getCode());
        assertEquals("Pagamento Rejeitado", LunchStatusEnum.PAYMENT_REJECTED.getDescription());

        assertEquals("IN_PREPARATION", LunchStatusEnum.IN_PREPARATION.getCode());
        assertEquals("Em Preparação", LunchStatusEnum.IN_PREPARATION.getDescription());

        assertEquals("READY", LunchStatusEnum.READY.getCode());
        assertEquals("Pronto", LunchStatusEnum.READY.getDescription());

        assertEquals("FINISHED", LunchStatusEnum.FINISHED.getCode());
        assertEquals("Finalizado", LunchStatusEnum.FINISHED.getDescription());
    }

    @Test
    @DisplayName("Should return correct enum for valid code")
    void testToEnumWithValidCode() {
        assertEquals(LunchStatusEnum.RECEIVED, LunchStatusEnum.toEnum("RECEIVED"));
        assertEquals(LunchStatusEnum.PAYMENT_APPROVED, LunchStatusEnum.toEnum("PAYMENT_APPROVED"));
        assertEquals(LunchStatusEnum.PAYMENT_CREATE_FAILED, LunchStatusEnum.toEnum("PAYMENT_CREATE_FAILED"));
        assertEquals(LunchStatusEnum.PAYMENT_REJECTED, LunchStatusEnum.toEnum("PAYMENT_REJECTED"));
        assertEquals(LunchStatusEnum.IN_PREPARATION, LunchStatusEnum.toEnum("IN_PREPARATION"));
        assertEquals(LunchStatusEnum.READY, LunchStatusEnum.toEnum("READY"));
        assertEquals(LunchStatusEnum.FINISHED, LunchStatusEnum.toEnum("FINISHED"));
    }

    @Test
    @DisplayName("Should throw exception for invalid code")
    void testToEnumWithInvalidCode() {
        Exception exception = assertThrows(DataIntegrityException.class, () ->
                LunchStatusEnum.toEnum("INVALID_CODE")
        );
        assertEquals("Status não reconhecido: INVALID_CODE", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for null code")
    void testToEnumWithNullCode() {
        Exception exception = assertThrows(DataIntegrityException.class, () ->
                LunchStatusEnum.toEnum(null)
        );
        assertEquals("Status não reconhecido: null", exception.getMessage());
    }


}
