package com.ari.majumundur.Service.impl;

import com.ari.majumundur.Models.Entities.*;
import com.ari.majumundur.Models.Request.OrderRequest;
import com.ari.majumundur.Models.Request.ProductRequest;
import com.ari.majumundur.Models.Response.CustomerResponse;
import com.ari.majumundur.Models.Response.OrderDetailResponse;
import com.ari.majumundur.Models.Response.OrderResponse;
import com.ari.majumundur.Models.Response.ProductResponse;
import com.ari.majumundur.Repository.OrderRepository;
import com.ari.majumundur.Repository.ProductPriceRepository;
import com.ari.majumundur.Repository.ProductRepository;
import com.ari.majumundur.Service.CustomerService;
import com.ari.majumundur.Service.OrderService;
import com.ari.majumundur.Service.ProductPriceService;
import com.ari.majumundur.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductPriceService productPriceService;
    private final ProductPriceRepository productRepository;
    @Override
    public OrderResponse createNewOrder(OrderRequest orderRequest) {
        Customer customer= customerService.getById(orderRequest.getCustomerId());

        List<OrderDetail> orderDetailList = orderRequest.getOrderDetail().stream().map(orderDetail ->{
            ProductPrice productPrice = productPriceService.getById(orderDetail.getProductPriceId());
            return OrderDetail.builder()
                    .productPrice(productPrice)
                    .quantity(orderDetail.getQuantity()).build();
        }).toList();

        Order order =Order.builder()
                .customerId(customer)
                .transactionDate(Date.valueOf(LocalDate.now()))
                .orderDetails(orderDetailList)
                .build();
        orderRepository.saveAndFlush(order);


        List<OrderDetailResponse> orderDetails = order.getOrderDetails().stream().map(orderDetail -> {
            orderDetail.setOrder(order);
            //CHANGE PRODUCT QUANTITY IN PRODUCT PRICE
            ProductPrice productPrice = orderDetail.getProductPrice();
            Product currProduct = productPrice.getProduct();
            productPrice.setStock(productPrice.getStock() - orderDetail.getQuantity());

            productRepository.save(productPrice);

            return OrderDetailResponse.builder()
                    .orderDetailId(orderDetail.getId())
                    .quantity(orderDetail.getQuantity())
                    //convert product to product response
                    .productResponse(ProductResponse.builder()
                            .productId(currProduct.getId())
                            .productName(currProduct.getName())
                            .productDescription(currProduct.getDescription())
                            .storeName(productPrice.getStore().getName())
                            .price(productPrice.getPrice())
                            .build())
                    .build();
        }).toList();

        return OrderResponse.builder()
                .orderId(order.getId())
                .transDate(order.getTransactionDate().toLocalDate())
                .customer(customer)
                .orderDetails(orderDetails)
                .build();
    }

    @Override
    public List<OrderResponse> getAll() {
        return null;
    }

    @Override
    public OrderResponse getById(String orderId) {
        return null;
    }
}
