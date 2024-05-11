package com.ari.majumundur.Repository;

import com.ari.majumundur.Models.Entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailResponse extends JpaRepository<OrderDetail, String> {
}
