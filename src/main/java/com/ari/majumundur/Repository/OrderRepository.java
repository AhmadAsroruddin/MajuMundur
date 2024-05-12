package com.ari.majumundur.Repository;

import com.ari.majumundur.Models.Entities.Customer;
import com.ari.majumundur.Models.Entities.Order;
import com.ari.majumundur.Models.Entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findOrderByCustomerId(Customer customer);
}
