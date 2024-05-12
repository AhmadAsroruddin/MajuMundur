package com.ari.majumundur.Repository;

import com.ari.majumundur.Models.Entities.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, String> {
    Optional<List<ProductPrice>> findProductPriceByIsActiveTrue();
    Optional<List<ProductPrice>> findProductPriceByStoreId(String merchantId);
}
