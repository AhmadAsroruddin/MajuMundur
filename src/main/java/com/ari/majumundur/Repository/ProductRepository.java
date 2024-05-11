package com.ari.majumundur.Repository;

import com.ari.majumundur.Models.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
