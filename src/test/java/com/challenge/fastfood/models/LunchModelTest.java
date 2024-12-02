package com.challenge.fastfood.models;

import com.challenge.fastfood.enums.LunchStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LunchModelTest {

    private LunchModel lunchModel;

    @BeforeEach
    void setUp() {
        lunchModel = new LunchModel();
        lunchModel.setId(1L);
        lunchModel.setCpf("12345678900");
        lunchModel.setStatus(LunchStatusEnum.RECEIVED);
        lunchModel.setClientEmail("client@example.com");
        lunchModel.setValue(new BigDecimal("29.99"));
        lunchModel.setPayments(Collections.emptyList());
        lunchModel.setCreationDate(LocalDateTime.now());
        lunchModel.setLastUpdateDate(LocalDateTime.now());
    }

    @Test
    void testGettersAndSetters() {
        assertThat(lunchModel.getId()).isEqualTo(1L);
        assertThat(lunchModel.getCpf()).isEqualTo("12345678900");
        assertThat(lunchModel.getStatus()).isEqualTo(LunchStatusEnum.RECEIVED);
        assertThat(lunchModel.getClientEmail()).isEqualTo("client@example.com");
        assertThat(lunchModel.getValue()).isEqualTo(new BigDecimal("29.99"));
        assertThat(lunchModel.getPayments()).isEmpty();
        assertThat(lunchModel.getCreationDate()).isNotNull();
        assertThat(lunchModel.getLastUpdateDate()).isNotNull();
    }

    @Test
    void testSetStatusWithValidEnum() {
        lunchModel.setStatus(LunchStatusEnum.FINISHED);
        assertThat(lunchModel.getStatus()).isEqualTo(LunchStatusEnum.FINISHED);
    }

    @Test
    void testSetStatusWithNullEnum() {
        assertThrows(NullPointerException.class, () -> lunchModel.setStatus(null));
    }

    @Test
    void testAddPayments() {
        PaymentModel payment1 = new PaymentModel();
        PaymentModel payment2 = new PaymentModel();

        lunchModel.setPayments(Arrays.asList(payment1, payment2));

        assertThat(lunchModel.getPayments()).hasSize(2);
        assertThat(lunchModel.getPayments()).containsExactly(payment1, payment2);
    }

    @Test
    void testUpdateLastModifiedDate() {
        LocalDateTime originalDate = lunchModel.getLastUpdateDate();
        lunchModel.setLastUpdateDate(LocalDateTime.now().plusDays(1));

        assertThat(lunchModel.getLastUpdateDate()).isAfter(originalDate);
    }

    @Test
    void testCreatedDateShouldNotBeNull() {
        assertThat(lunchModel.getCreationDate()).isNotNull();
    }

    @Test
    void testLastModifiedDateShouldNotBeNull() {
        assertThat(lunchModel.getLastUpdateDate()).isNotNull();
    }


}
