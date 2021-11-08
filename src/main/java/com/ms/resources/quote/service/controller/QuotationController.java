package com.ms.resources.quote.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.resources.quote.service.entity.Quotation;
import com.ms.resources.quote.service.service.QuotationService;
import com.ms.resources.quote.service.service.QuoteFromService;
import com.ms.resources.quote.service.service.QuoteToService;
import com.ms.resources.quote.service.util.DocumentUtil;
import com.ms.resources.quote.service.vm.FileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/quote")
public class QuotationController {

    @Autowired
    private QuotationService quotationService;
    @Autowired
    private QuoteToService quoteToService;
    @Autowired
    private QuoteFromService quoteFromService;

    private DocumentUtil documentUtil = DocumentUtil.getInstance();

    @GetMapping("/retrieve/all")
    public ResponseEntity<List<Quotation>> findAllQuotation() {
        return new ResponseEntity<List<Quotation>>(quotationService.findAllQuotation(), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Quotation> quotationSubmit(@RequestBody Quotation quotation) {

        Quotation results = new Quotation();
        if (null != quotation) {
            results = quotationService.createQuotation(quotation);
        }

        return new ResponseEntity<Quotation>(results, HttpStatus.CREATED);
    }

    //@GetMapping("/retrieve/quotationId/{quotationId}")
    //public ResponseEntity<Optional> findQuotationById(@PathVariable String quotationId) {
    @GetMapping("/retrieve/quotationId")
    public ResponseEntity<Optional> findQuotationById(@RequestParam String quotationId) {
        return new ResponseEntity<Optional>(quotationService.findById(quotationId), HttpStatus.OK);
    }

    @GetMapping("/retrieve/quotationNo")
    public ResponseEntity<Quotation> findQuotationByQuotationNo(@RequestParam String quotationNo) {
        return new ResponseEntity<Quotation>(quotationService.findByQuotationNo(quotationNo), HttpStatus.OK);
    }

    @GetMapping("/generate/{quotationNo}")
    public ResponseEntity<FileResponse> generateQuotation(@PathVariable String quotationNo) {
        Quotation tempObj = quotationService.findByQuotationNo(quotationNo);

        FileResponse result = null;
        try {
            if (null != tempObj) {
                ObjectMapper objectMapper = new ObjectMapper();
                result = documentUtil.exportReport(tempObj);
                log.info("Generated Content : " + objectMapper.writeValueAsString(result));
            }
        }catch (Exception e){
            log.error("Error generating quotation ! " + e.getMessage());
        }

        return new ResponseEntity<FileResponse>(result, HttpStatus.OK);
    }
}
