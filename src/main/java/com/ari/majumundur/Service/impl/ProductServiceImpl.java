package com.ari.majumundur.Service.impl;

import com.ari.majumundur.Models.Entities.Merchant;
import com.ari.majumundur.Models.Entities.Product;
import com.ari.majumundur.Models.Entities.ProductPrice;
import com.ari.majumundur.Models.Request.ProductRequest;
import com.ari.majumundur.Models.Response.ProductResponse;
import com.ari.majumundur.Repository.MerchantRepository;
import com.ari.majumundur.Repository.ProductRepository;
import com.ari.majumundur.Service.MerchantService;
import com.ari.majumundur.Service.ProductPriceService;
import com.ari.majumundur.Service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductPriceService productPriceService;
    private final ProductRepository productRepository;
    private final MerchantService merchantService;
    @Override
    public ProductResponse createProductAndPrice(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getProductName())
                .description(productRequest.getProductDescription())
                .build();
        productRepository.saveAndFlush(product);

        Merchant merchant =merchantService.getMerchantById(productRequest.getStoreId());

        ProductPrice price = ProductPrice.builder()
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .product(product)
                .store(merchant)
                .isActive(true).build();
        productPriceService.create(price);

        return ProductResponse.builder()
                .productId(product.getId())
                .storeName(merchant.getName())
                .productName(product.getName())
                .productDescription(product.getDescription())
                .price(price.getPrice())
                .stock(price.getStock())
                .build();
    }

    @Override
    public Page<ProductResponse> getAllByNameOrPrice(String name, Long maxPrice, Integer page, Integer size) {
        return null;
    }

    @Override
    @Transactional
    public ProductResponse update(String priceId,ProductRequest productRequest) {
        ProductPrice productPrice = productPriceService.getById(priceId);
        productPrice.setPrice(productRequest.getPrice());
        productPrice.setStock(productRequest.getStock());

        Product product = productRepository.findById(productPrice.getProduct().getId()).orElseThrow(() -> new EntityNotFoundException("No Product Found"));

        product.setName(productRequest.getProductName());
        product.setDescription(productRequest.getProductDescription());
        productPrice.setProduct(product);


        productPriceService.create(productPrice);

        return ProductResponse.builder()
                .productId(product.getId())
                .productName(product.getName())
                .productDescription(product.getDescription())
                .price(productPrice.getPrice())
                .stock(productPrice.getStock())
                .build();
    }


}
