package com.ms.resources.quote.service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quotation {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String quotationId;
    @Column(unique = true)
    private String quotationNo;
    private Date quotationDate;
    private String quotationSST;
    private Double quotationTotal;
    @Lob
    private byte[] quotationLogo;
    @OneToOne(mappedBy = "quotation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private QuoteFrom quoteFrom;
    @OneToOne(mappedBy = "quotation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private QuoteTo quoteTo;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "quotationId")
    private List<Product> products;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "quotationId")
    private List<TermsAndCondition> termsAndConditions;

}
