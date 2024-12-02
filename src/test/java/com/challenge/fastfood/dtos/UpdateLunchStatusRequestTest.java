package com.challenge.fastfood.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateLunchStatusRequestTest {

    private UpdateLunchStatusRequest request;

    @BeforeEach
    void setUp() {
        request = new UpdateLunchStatusRequest();
    }

    @Test
    void testNoArgsConstructor() {
        assertThat(request.getNumberLunch()).isNull();
        assertThat(request.getStatus()).isNull();
    }

    @Test
    void testAllArgsConstructor() {
        request = new UpdateLunchStatusRequest(123L, "PENDING");

        assertThat(request.getNumberLunch()).isEqualTo(123L);
        assertThat(request.getStatus()).isEqualTo("PENDING");
    }

    @Test
    void testSettersAndGetters() {
        request.setNumberLunch(456L);
        request.setStatus("COMPLETED");

        assertThat(request.getNumberLunch()).isEqualTo(456L);
        assertThat(request.getStatus()).isEqualTo("COMPLETED");
    }

    @Test
    void testEqualsAndHashCode() {
        UpdateLunchStatusRequest request1 = new UpdateLunchStatusRequest(123L, "PENDING");
        UpdateLunchStatusRequest request2 = new UpdateLunchStatusRequest(123L, "PENDING");
        UpdateLunchStatusRequest request3 = new UpdateLunchStatusRequest(456L, "COMPLETED");

        assertThat(request1).isEqualTo(request2);
        assertThat(request1).isNotEqualTo(request3);
        assertThat(request1.hashCode()).isEqualTo(request2.hashCode());
        assertThat(request1.hashCode()).isNotEqualTo(request3.hashCode());
    }

    @Test
    void testToString() {
        request = new UpdateLunchStatusRequest(123L, "PENDING");
        String expected = "UpdateLunchStatusRequest(numberLunch=123, status=PENDING)";
        assertThat(request.toString()).isEqualTo(expected);
    }
}
