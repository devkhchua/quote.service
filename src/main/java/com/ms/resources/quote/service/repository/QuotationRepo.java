package com.ms.resources.quote.service.repository;

import com.ms.resources.quote.service.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepo extends JpaRepository<Quotation, String> {
    public Quotation findByQuotationNo(String quotationNo);
}
