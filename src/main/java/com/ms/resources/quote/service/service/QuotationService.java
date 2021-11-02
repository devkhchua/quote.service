package com.ms.resources.quote.service.service;

import com.ms.resources.quote.service.entity.Quotation;

import java.util.List;
import java.util.Optional;

public interface QuotationService {
    public List<Quotation> findAllQuotation();
    public Quotation createQuotation(Quotation quotation);
    public Optional<Quotation> findById(String quotationId);
    public Quotation findByQuotationNo(String quotationNo);
}
