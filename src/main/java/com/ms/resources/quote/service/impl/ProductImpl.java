package com.ms.resources.quote.service.impl;

import com.ms.resources.quote.service.entity.Product;
import com.ms.resources.quote.service.repository.ProductRepo;
import com.ms.resources.quote.service.service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Data
@RequiredArgsConstructor
public class ProductImpl implements ProductService {
    private final ProductRepo productRepo;
    @Override
    public List<Product> findAllProduct() {
        return productRepo.findAll();
    }
}
