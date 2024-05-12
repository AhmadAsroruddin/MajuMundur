package com.ari.majumundur.Models.Response;

import com.ari.majumundur.Models.Entities.Customer;
import com.ari.majumundur.Models.Entities.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class CustomerBuyer {
    CustomerResponse customer;
    List<ProductResponse> productPrice;
}
