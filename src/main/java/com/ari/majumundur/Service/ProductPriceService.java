package com.ari.majumundur.Service;

import com.ari.majumundur.Models.Entities.ProductPrice;
import com.ari.majumundur.Models.Response.ProductPriceResponse;
import com.ari.majumundur.Models.Response.ProductResponse;

import java.util.List;

public interface ProductPriceService {
    ProductPrice create(ProductPrice productPrice);
    ProductPrice getById(String id);

    List<ProductResponse> getAllProduct();

    ProductResponse softDelete(String productPriceId);
    String hardDelete(String productPriceId);
}
