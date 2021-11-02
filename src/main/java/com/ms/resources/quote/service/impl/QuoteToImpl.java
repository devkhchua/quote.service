package com.ms.resources.quote.service.impl;

import com.ms.resources.quote.service.entity.QuoteTo;
import com.ms.resources.quote.service.repository.QuoteToRepo;
import com.ms.resources.quote.service.service.QuoteToService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class QuoteToImpl implements QuoteToService {
    private final QuoteToRepo quoteToRepo;
    @Override
    public List<QuoteTo> findAllQuoteTo() {
        return quoteToRepo.findAll();
    }

    @Override
    public QuoteTo createQuoteTo(QuoteTo quoteTo) {
        return quoteToRepo.save(quoteTo);
    }
}
