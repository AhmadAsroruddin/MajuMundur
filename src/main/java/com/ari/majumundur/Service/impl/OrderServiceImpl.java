package com.ari.majumundur.Service.impl;

import com.ari.majumundur.Models.Entities.*;
import com.ari.majumundur.Models.Request.OrderRequest;
import com.ari.majumundur.Models.Request.ProductRequest;
import com.ari.majumundur.Models.Response.*;
import com.ari.majumundur.Repository.OrderDetailRepository;
import com.ari.majumundur.Repository.OrderRepository;
import com.ari.majumundur.Repository.ProductPriceRepository;
import com.ari.majumundur.Repository.ProductRepository;
import com.ari.majumundur.Service.CustomerService;
import com.ari.majumundur.Service.OrderService;
import com.ari.majumundur.Service.ProductPriceService;
import com.ari.majumundur.Service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductPriceService productPriceService;
    private final ProductPriceRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;
    @Override
    @Transactional
    public OrderResponse createNewOrder(OrderRequest orderRequest) {
        Customer customer= customerService.getById(orderRequest.getCustomerId());
        AtomicLong total = new AtomicLong(0L);
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
            int newStock = productPrice.getStock() - orderDetail.getQuantity();
            if (newStock < 0) {
                throw new RuntimeException("Not enough stock available for product: " + currProduct.getName());
            }
            productPrice.setStock(newStock);
            productRepository.save(productPrice);

            total.addAndGet(orderDetail.getQuantity() * orderDetail.getProductPrice().getPrice());

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

        int pointsEarned = total.get() > 20000 ? 40 : 20;

        // Update points earned by the customer
        customer.setPoints(customer.getPoints() + pointsEarned);
        customerService.save(customer);


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

    @Override
    public List<CustomerBuyer> findCustomersByStore(String storeId) {
        List<ProductPrice> productPrices = productRepository.findProductPriceByStoreId(storeId).orElseThrow(()->new EntityNotFoundException("No Data Found"));
        List<Customer> customers = new ArrayList<>();

        for (ProductPrice productPrice : productPrices) {
            List<OrderDetail> orderDetails = orderDetailRepository.findOrderDetailByProductPrice(productPrice).orElseThrow(()->new EntityNotFoundException("No Data Found"));

            for (OrderDetail orderDetail : orderDetails) {
                Order order = orderDetail.getOrder();
                Customer customer = customerService.getById(order.getCustomerId().getId());
                customers.add(customer);
            }
        }


        // Remove duplicates
        Set<Customer> uniqueCustomers = new HashSet<>(customers);
        return uniqueCustomers.stream().map(customer -> {
            List<ProductPrice> productPriceList = getProductPricesByCustomer(customer.getId());
            return CustomerBuyer.builder()
                    .customer(
                            CustomerResponse.builder()
                                    .name(customer.getName())
                                    .email(customer.getEmail())
                                    .address(customer.getAddress())
                                    .points(customer.getPoints())
                                    .mobile_phone(customer.getMobilePhone())
                                    .build()
                    )
                    .productPrice(productPriceList.stream().map(data ->{
                        return ProductResponse.builder()
                                .productName(data.getProduct().getName())
                                .productDescription(data.getProduct().getDescription())
                                .storeName(data.getStore().getName())
                                .build();
                    }).toList())
                    .build();
        }).toList();
    }
    public List<ProductPrice> getProductPricesByCustomer(String customerId) {
        Customer customer = customerService.getById(customerId);
        // Mencari semua order yang terkait dengan customer
        List<Order> orders = orderRepository.findOrderByCustomerId(customer);

        // List untuk menyimpan semua product price yang dibeli oleh customer
        List<ProductPrice> productPrices = new ArrayList<>();

        // Iterasi setiap order
        for (Order order : orders) {
            // Iterasi setiap order detail dalam order tersebut
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                // Mengambil product price dari setiap order detail dan menambahkannya ke daftar product price
                productPrices.add(orderDetail.getProductPrice());
            }
        }

        return productPrices;
    }

}
