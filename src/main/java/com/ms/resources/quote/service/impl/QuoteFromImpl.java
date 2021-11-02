package com.ms.resources.quote.service.impl;

import com.ms.resources.quote.service.entity.QuoteFrom;
import com.ms.resources.quote.service.repository.QuoteFromRepo;
import com.ms.resources.quote.service.service.QuoteFromService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class QuoteFromImpl implements QuoteFromService {
    private final QuoteFromRepo quoteFromRepo;
    @Override
    public List<QuoteFrom> findAllQuoteFrom() {
        return quoteFromRepo.findAll();
    }

    @Override
    public QuoteFrom createQuoteFrom(QuoteFrom quoteFrom) {
        return quoteFromRepo.save(quoteFrom);
    }
}
