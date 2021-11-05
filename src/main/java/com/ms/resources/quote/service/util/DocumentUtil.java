package com.ms.resources.quote.service.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.resources.quote.service.entity.Quotation;
import com.ms.resources.quote.service.vm.DocumentParam;
import com.ms.resources.quote.service.vm.FileResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
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

            //ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.scale(quotation.getQuotationLogo(), 275, 595));
            ByteArrayInputStream logoByteInputStream = new ByteArrayInputStream(quotation.getQuotationLogo());
            ByteArrayInputStream signByteInputStream = new ByteArrayInputStream(quotation.getQuotationSign());
            parameters.put("logo", logoByteInputStream);
            parameters.put("sign", signByteInputStream);

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

    public byte[] scale(byte[] fileData, int width, int height) throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        try {
            BufferedImage img = ImageIO.read(in);
            log.info("Height : " + img.getHeight());
            log.info("Width : " + img.getWidth());
            if(height == 0) {
                height = (width * img.getHeight())/ img.getWidth();
            }
            if(width == 0) {
                width = (height * img.getWidth())/ img.getHeight();
            }
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_FAST);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "jpg", buffer);

            return buffer.toByteArray();
        } catch (Exception e) {
            throw new Exception("Error in scaling image!");
        }
    }
}
