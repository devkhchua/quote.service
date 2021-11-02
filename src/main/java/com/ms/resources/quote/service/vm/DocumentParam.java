package com.ms.resources.quote.service.vm;

import com.ms.resources.quote.service.entity.Product;
import com.ms.resources.quote.service.entity.Quotation;
import com.ms.resources.quote.service.entity.TermsAndCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JasperReport;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentParam {
    private String quotationNo;
    private Date quotationDate;
    private String fromContact;
    private String fromName;
    private String fromEmail;
    private String fromAddress;
    private String toName;
    private String toEmail;
    private String toContact;
    private String toAddress;
    private String totalAmount;
    private List<Product> products;
    private List<TermsAndCondition> termsAndConditions;

    public DocumentParam(Quotation quotation) {
        quotationNo = quotation.getQuotationNo();
        quotationDate = quotation.getQuotationDate();
        fromContact = quotation.getQuoteFrom().getBusinessContactNo();
        fromName = quotation.getQuoteFrom().getBusinessName();
        fromEmail = quotation.getQuoteFrom().getBusinessEmail();
        fromAddress = quotation.getQuoteFrom().getBusinessAddress();
        toName = quotation.getQuoteTo().getBusinessName();
        toEmail = quotation.getQuoteTo().getBusinessEmail();
        toContact = quotation.getQuoteTo().getBusinessContactNo();
        toAddress = quotation.getQuoteTo().getBusinessAddress();
        totalAmount = quotation.getQuotationTotal().toString();
        products = quotation.getProducts();
        termsAndConditions = quotation.getTermsAndConditions();
    }
}
