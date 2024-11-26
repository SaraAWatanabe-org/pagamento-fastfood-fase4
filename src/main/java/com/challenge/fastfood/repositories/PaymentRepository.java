package com.challenge.fastfood.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.challenge.fastfood.models.PaymentModel;

public interface PaymentRepository extends MongoRepository<PaymentModel, UUID>{

	Optional<PaymentModel> findByNumberLunch(Long numerLunch);
	Optional<PaymentModel> findPaymentByTransactionId(String transactionId);

}
