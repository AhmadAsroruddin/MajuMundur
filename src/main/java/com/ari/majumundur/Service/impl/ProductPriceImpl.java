package com.ari.majumundur.Service.impl;

import com.ari.majumundur.Models.Entities.ProductPrice;
import com.ari.majumundur.Models.Response.ProductPriceResponse;
import com.ari.majumundur.Models.Response.ProductResponse;
import com.ari.majumundur.Repository.ProductPriceRepository;
import com.ari.majumundur.Repository.ProductRepository;
import com.ari.majumundur.Service.OrderDetailService;
import com.ari.majumundur.Service.ProductPriceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductPriceImpl  implements ProductPriceService {
    private final ProductPriceRepository productPriceRepository;
    private final ProductRepository productRepository;
    private final OrderDetailService orderDetailService;

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
        List<ProductPrice> productPrices = productPriceRepository.findProductPriceByIsActiveTrue().orElseThrow(() -> new EntityNotFoundException("No Product"));

        return productPrices.stream().map(data ->{
            return ProductResponse.builder()
                    .productId(data.getId())
                    .productName(data.getProduct().getName())
                    .productDescription(data.getProduct().getDescription())
                    .price(data.getPrice())
                    .stock(data.getStock())
                    .storeName(data.getStore().getName())
                    .isActive(data.getIsActive())
                    .build();
        }).toList();
    }

    @Override
    public ProductResponse softDelete(String productPriceId) {
        ProductPrice productPrice = productPriceRepository.findById(productPriceId).orElseThrow(()-> new EntityNotFoundException("Product Tidak ditemukan"));

        productPrice.setIsActive(false);
        productPriceRepository.save(productPrice);
        return ProductResponse.builder()
                .productName(productPrice.getProduct().getName())
                .productId(productPrice.getProduct().getId())
                .productDescription(productPrice.getProduct().getDescription())
                .price(productPrice.getPrice())
                .stock(productPrice.getStock())
                .isActive(productPrice.getIsActive())
                .build();
    }

    @Override
    @Transactional
    public String hardDelete(String productPriceId) {
        ProductPrice productPrice = productPriceRepository.findById(productPriceId).orElseThrow(()-> new EntityNotFoundException("Product Tidak ditemukan"));

        orderDetailService.delete(productPriceId);
        productPriceRepository.delete(productPrice);

        return null;
    }

}
