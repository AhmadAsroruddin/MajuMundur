package com.ari.majumundur.Repository;

import com.ari.majumundur.Models.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
