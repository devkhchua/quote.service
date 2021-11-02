package com.ms.resources.quote.service.service;

import com.ms.resources.quote.service.entity.QuoteFrom;

import java.util.List;

public interface QuoteFromService {
    public List<QuoteFrom> findAllQuoteFrom();
    public QuoteFrom createQuoteFrom(QuoteFrom quoteFrom);
}
