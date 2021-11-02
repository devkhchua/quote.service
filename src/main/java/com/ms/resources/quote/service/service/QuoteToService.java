package com.ms.resources.quote.service.service;

import com.ms.resources.quote.service.entity.QuoteTo;

import java.util.List;

public interface QuoteToService {
    public List<QuoteTo> findAllQuoteTo();
    public QuoteTo createQuoteTo(QuoteTo quoteTo);
}
