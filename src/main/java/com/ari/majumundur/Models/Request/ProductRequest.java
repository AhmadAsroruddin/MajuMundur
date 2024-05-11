package com.ari.majumundur.Models.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductRequest {
    private String productId;
    @NotBlank(message = "product name is required")
    private String productName;
    private String productDescription;
    private Long price;
    private Integer stock;
    @NotEmpty(message = "store id is required")
    private String storeId;
}
