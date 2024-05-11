package com.ari.majumundur.Service;

import com.ari.majumundur.Models.Request.ProductRequest;
import com.ari.majumundur.Models.Response.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductResponse createProductAndPrice(ProductRequest productRequest);
    ProductResponse getProductDetail(String id);

    List<ProductResponse> getMerchantProduct(String merchantId);

    Page<ProductResponse> getAllByNameOrPrice(String name, Long maxPrice, Integer page, Integer size);

    ProductResponse update(ProductRequest productRequest);
}
