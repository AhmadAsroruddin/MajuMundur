package com.ari.majumundur.Service;

import com.ari.majumundur.Models.Request.OrderDetailRequest;
import com.ari.majumundur.Models.Response.CustomerBuyer;
import com.ari.majumundur.Models.Response.CustomerResponse;
import com.ari.majumundur.Models.Response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {
    String delete(String productId);
    List<CustomerBuyer> findCustomer(String storeId);
}
