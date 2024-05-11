package com.ari.majumundur.Service;

import com.ari.majumundur.Models.Entities.ProductPrice;

public interface ProductPriceService {
    ProductPrice create(ProductPrice productPrice);
    ProductPrice getById(String id);

    ProductPrice getActive(String id, Boolean status);
}
