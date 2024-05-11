package com.ari.majumundur.Repository;

import com.ari.majumundur.Models.Entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {
}
