package com.ari.majumundur.Service;

import com.ari.majumundur.Models.Entities.Customer;
import com.ari.majumundur.Models.Request.OrderRequest;
import com.ari.majumundur.Models.Response.CustomerBuyer;
import com.ari.majumundur.Models.Response.OrderResponse;

import java.util.List;

public interface OrderService {
    public OrderResponse createNewOrder(OrderRequest orderRequest);

    List<OrderResponse> getAll();
    OrderResponse getById(String orderId);

    List<CustomerBuyer> findCustomersByStore(String storeId);
}
