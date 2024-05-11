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
    public ProductResponse getProductDetail(String id) {
        return null;
    }

    @Override
    public List<ProductResponse> getMerchantProduct(String merchantId) {
        return null;
    }

    @Override
    public Page<ProductResponse> getAllByNameOrPrice(String name, Long maxPrice, Integer page, Integer size) {
        return null;
    }

    @Override
    public ProductResponse update(ProductRequest productRequest) {
        return null;
    }
}
