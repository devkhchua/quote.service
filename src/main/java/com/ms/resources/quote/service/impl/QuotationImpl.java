package com.ms.resources.quote.service.impl;

import com.ms.resources.quote.service.entity.Quotation;
import com.ms.resources.quote.service.repository.QuotationRepo;
import com.ms.resources.quote.service.repository.QuoteFromRepo;
import com.ms.resources.quote.service.repository.QuoteToRepo;
import com.ms.resources.quote.service.service.QuotationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class QuotationImpl implements QuotationService {
    private final QuotationRepo quotationRepo;
    private final QuoteToRepo quoteToRepo;
    private final QuoteFromRepo quoteFromRepo;

    @Override
    public List<Quotation> findAllQuotation() {
        return quotationRepo.findAll();
    }

    @Override
    public Quotation createQuotation(Quotation quotation) {
        Quotation results = new Quotation();
        results = quotationRepo.save(quotation);
        results.setQuoteFrom(quoteFromRepo.save(quotation.getQuoteFrom()));
        results.setQuoteTo(quoteToRepo.save(quotation.getQuoteTo()));
        return results;
    }

    @Override
    public Optional<Quotation> findById(String quotationId) {
        return quotationRepo.findById(quotationId);
    }

    @Override
    public Quotation findByQuotationNo(String quotationNo) {
        return quotationRepo.findByQuotationNo(quotationNo);
    }
}
