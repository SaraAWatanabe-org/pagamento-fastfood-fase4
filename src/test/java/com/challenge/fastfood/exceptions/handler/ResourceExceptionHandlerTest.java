package com.challenge.fastfood.exceptions.handler;

import com.challenge.fastfood.exceptions.DataIntegrityException;
import com.challenge.fastfood.exceptions.ObjectNotFoundException;
import com.challenge.fastfood.exceptions.PaymentCreateFailException;
import com.challenge.fastfood.exceptions.PaymentException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    private HttpServletRequest mockRequest;

    @BeforeEach
    void setUp() {
        exceptionHandler = new ResourceExceptionHandler();
        mockRequest = mock(HttpServletRequest.class);
    }

    @Test
    void testObjectNotFound() {
        ObjectNotFoundException exception = new ObjectNotFoundException("Object not found");
        ResponseEntity<StandardError> response = exceptionHandler.objectNotFound(exception, mockRequest);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
        assertThat(response.getBody().getMsg()).isEqualTo("Object not found");
        assertThat(response.getBody().getStatus()).isEqualTo(404);
    }

    @Test
    void testDataIntegrity() {
        DataIntegrityException exception = new DataIntegrityException("Data integrity violation");
        ResponseEntity<StandardError> response = exceptionHandler.dataIntegrity(exception, mockRequest);

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMsg()).isEqualTo("Data integrity violation");
        assertThat(response.getBody().getStatus()).isEqualTo(400);
    }

    @Test
    void testMethodArgumentNotValid() {
        FieldError fieldError = new FieldError("objectName", "field", "Invalid value");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<StandardError> response = exceptionHandler.methodArgumentNotValid(exception, mockRequest);

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMsg()).isEqualTo("Invalid value");
        assertThat(response.getBody().getStatus()).isEqualTo(400);
    }

    @Test
    void testPaymentException() {
        PaymentException exception = new PaymentException("Payment failed");
        ResponseEntity<StandardError> response = exceptionHandler.paymentException(exception, mockRequest);

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody().getMsg()).isEqualTo("Payment failed");
        assertThat(response.getBody().getStatus()).isEqualTo(400);
    }

    @Test
    void testPaymentCreateFailException() {
        PaymentCreateFailException exception = new PaymentCreateFailException("Payment creation failed");
        ResponseEntity<StandardError> response = exceptionHandler.paymentCreateFailException(exception, mockRequest);

        assertThat(response.getStatusCodeValue()).isEqualTo(500);
        assertThat(response.getBody().getMsg()).isEqualTo("Payment creation failed");
        assertThat(response.getBody().getStatus()).isEqualTo(500);
    }


}
