package com.ari.majumundur.Models.Response;

import com.ari.majumundur.Models.Entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderResponse {
    private String orderId;
    private LocalDate transDate;
    private Customer customer;
    private List<OrderDetailResponse> orderDetails;
}