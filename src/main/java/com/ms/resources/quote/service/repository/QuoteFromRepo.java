package com.ms.resources.quote.service.repository;

import com.ms.resources.quote.service.entity.QuoteFrom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteFromRepo extends JpaRepository<QuoteFrom, String> {
}
