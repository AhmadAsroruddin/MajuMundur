package com.ari.majumundur.Service.impl;

import com.ari.majumundur.Models.Request.OrderDetailRequest;
import com.ari.majumundur.Models.Response.OrderDetailResponse;
import com.ari.majumundur.Service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    @Override
    public OrderDetailResponse create(OrderDetailRequest orderDetailRequest) {
        return null;
    }
}
