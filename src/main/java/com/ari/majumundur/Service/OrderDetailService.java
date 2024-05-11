package com.ari.majumundur.Service;

import com.ari.majumundur.Models.Request.OrderDetailRequest;
import com.ari.majumundur.Models.Response.OrderDetailResponse;

public interface OrderDetailService {
    OrderDetailResponse create(OrderDetailRequest orderDetailRequest);
}
