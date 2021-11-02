package com.ms.resources.quote.service.repository;

import com.ms.resources.quote.service.entity.QuoteTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteToRepo extends JpaRepository<QuoteTo, String> {
}
