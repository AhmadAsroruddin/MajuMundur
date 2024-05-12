package com.ari.majumundur.Service.impl;

import com.ari.majumundur.Models.Entities.Customer;
import com.ari.majumundur.Models.Entities.OrderDetail;
import com.ari.majumundur.Models.Entities.ProductPrice;
import com.ari.majumundur.Models.Request.OrderDetailRequest;
import com.ari.majumundur.Models.Response.CustomerBuyer;
import com.ari.majumundur.Models.Response.CustomerResponse;
import com.ari.majumundur.Models.Response.OrderDetailResponse;
import com.ari.majumundur.Repository.OrderDetailRepository;
import com.ari.majumundur.Repository.ProductPriceRepository;
import com.ari.majumundur.Service.CustomerService;
import com.ari.majumundur.Service.OrderDetailService;
import com.ari.majumundur.Service.ProductPriceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository repository;
    private final ProductPriceRepository productPriceRepository;
    private final CustomerService customerService;

    @Override
    public String delete(String productId) {
        ProductPrice productPrice = productPriceRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException(("Product Not Found")));
        repository.deleteOrderDetailByProductPrice(productPrice);
        return null;
    }

    @Override
    public List<CustomerBuyer> findCustomer(String merchantId) {
        List<ProductPrice> productPriceList = productPriceRepository.findProductPriceByStoreId(merchantId).orElseThrow(() ->new EntityNotFoundException("No Data Found"));
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (ProductPrice productPrice : productPriceList) {
            List<OrderDetail> orderDetails = repository.findOrderDetailByProductPrice(productPrice).orElseThrow(() ->new EntityNotFoundException("No Data Found"));


        }

        return null;
    }
}
