package com.example.application.service;

import com.example.application.exception.QuoteNotFoundException;
import com.example.application.model.Quote;
import com.example.application.repository.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class QuoteService {

	private final QuoteRepository quoteRepository;

	public Iterable<Quote> getAll() {
		return quoteRepository.findAll();
	}

	public Optional<Quote> getById(String quoteId) {
		return quoteRepository.findById(Integer.parseInt(quoteId));
	}

	public Quote save(Quote quote) {
		return quoteRepository.save(quote);
	}

	public Quote update(String quoteId, Quote quote) throws QuoteNotFoundException {
		Optional<Quote> retrievedQuote = quoteRepository.findById(Integer.parseInt(quoteId));
		if(retrievedQuote.isPresent()) {
			retrievedQuote.get().setQuote(quote.getQuote());
			retrievedQuote.get().setSource(quote.getSource());
			return quoteRepository.save(retrievedQuote.get());
		} else {
			throw new QuoteNotFoundException("Quote Not Found");
		}
	}

	public void delete(String quoteId) {
		quoteRepository.deleteById(Integer.parseInt(quoteId));
	}
}
