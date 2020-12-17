package com.example.application.repository;

import com.example.application.model.Quote;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Integer> {
}
