package com.ms.resources.quote.service.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.resources.quote.service.entity.Product;
import com.ms.resources.quote.service.entity.Quotation;
import com.ms.resources.quote.service.entity.QuoteFrom;
import com.ms.resources.quote.service.vm.DocumentParam;
import com.ms.resources.quote.service.vm.FileResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class DocumentUtil {

    public static DocumentUtil getInstance() {
        return new DocumentUtil();
    }

    public FileResponse exportReport(Quotation quotation) throws Exception {
        try {
            FileResponse response = new FileResponse();
            InputStream quotationStream = getClass().getResourceAsStream("/templates/quotation.jrxml");
            InputStream subReportStream = getClass().getResourceAsStream("/templates/quotation_details.jrxml");
            InputStream subReportStream2 = getClass().getResourceAsStream("/templates/quotation_tnc.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(quotationStream);
            JasperReport subReport = JasperCompileManager.compileReport(subReportStream);
            JasperReport subReport2 = JasperCompileManager.compileReport(subReportStream2);

            DocumentParam param = new DocumentParam(quotation);

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> parameters = objectMapper.convertValue(param, new TypeReference<Map<String, Object>>() {});
            parameters.put("subReport", subReport);
            parameters.put("subReport2", subReport2);
            parameters.put("products", param.getProducts());
            parameters.put("terms", param.getTermsAndConditions());

            List<DocumentParam> tempData = new ArrayList<>();
            tempData.add(param);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tempData);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            byte[] fileContent = JasperExportManager.exportReportToPdf(jasperPrint);

            response.setFileName("quotation.pdf");
            response.setFileSize(fileContent.length);
            response.setFileContent(fileContent);

            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
