package com.ari.majumundur.Service.impl;

import com.ari.majumundur.Models.Entities.ProductPrice;
import com.ari.majumundur.Repository.ProductPriceRepository;
import com.ari.majumundur.Service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductPriceImpl  implements ProductPriceService {
    private final ProductPriceRepository productPriceRepository;
    @Override
    public ProductPrice create(ProductPrice productPrice) {
        return productPriceRepository.save(productPrice);
    }

    @Override
    public ProductPrice getById(String id) {
        return null;
    }

    @Override
    public ProductPrice getActive(String id, Boolean status) {
        return null;
    }
}
