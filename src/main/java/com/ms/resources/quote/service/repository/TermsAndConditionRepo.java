package com.ms.resources.quote.service.repository;

import com.ms.resources.quote.service.entity.TermsAndCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsAndConditionRepo extends JpaRepository<TermsAndCondition, String> {
}
