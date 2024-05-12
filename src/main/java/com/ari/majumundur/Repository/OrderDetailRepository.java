package com.ari.majumundur.Repository;

import com.ari.majumundur.Models.Entities.Order;
import com.ari.majumundur.Models.Entities.OrderDetail;
import com.ari.majumundur.Models.Entities.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    void deleteOrderDetailByProductPrice(ProductPrice productPrice);
    Optional<List<OrderDetail>> findOrderDetailByProductPrice(ProductPrice productPrice);
    List<OrderDetail> findOrderDetailByOrder(Order order);
}
