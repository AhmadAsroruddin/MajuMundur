package com.ari.majumundur.Models.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductResponse {
    private String productId;
    private String storeName;
    private String productName;
    private String productDescription;
    private Long price;
    private Integer stock;
    private ProductPriceResponse productPriceResponse;
}
