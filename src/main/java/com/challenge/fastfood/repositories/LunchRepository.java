package com.challenge.fastfood.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.challenge.fastfood.models.LunchModel;

public interface LunchRepository extends MongoRepository<LunchModel, Long> {

}
