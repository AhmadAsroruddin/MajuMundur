package com.ari.majumundur.Service.impl;

import com.ari.majumundur.Models.Entities.ProductPrice;
import com.ari.majumundur.Models.Response.ProductResponse;
import com.ari.majumundur.Repository.ProductPriceRepository;
import com.ari.majumundur.Service.ProductPriceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return productPriceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product Price Not Found"));
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<ProductPrice> productPrices = productPriceRepository.findAll();

        return productPrices.stream().map(data ->{
            return ProductResponse.builder()
                    .productName(data.getProduct().getName())
                    .productDescription(data.getProduct().getDescription())
                    .price(data.getPrice())
                    .stock(data.getStock())
                    .storeName(data.getStore().getName())
                    .build();
        }).toList();
    }

    @Override
    public ProductPrice getActive(String id, Boolean status) {
        return null;
    }
}
