package com.ms.resources.quote.service.impl;

import com.ms.resources.quote.service.entity.TermsAndCondition;
import com.ms.resources.quote.service.repository.TermsAndConditionRepo;
import com.ms.resources.quote.service.service.TermsAndConditionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TermsAndConditionImpl implements TermsAndConditionService {
    private final TermsAndConditionRepo termsAndConditionRepo;
    @Override
    public List<TermsAndCondition> findAllTermsaAndCondition() {
        return termsAndConditionRepo.findAll();
    }
}
