package com.ari.majumundur.Repository;

import com.ari.majumundur.Models.Entities.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, String> {
    Optional<ProductPrice> findByProduct_IdAndIsActive(String id, Boolean status);
    Optional<ProductPrice> findProductPriceByStoreId(String merchantId);
}
