package com.ari.majumundur.Service.impl;

import com.ari.majumundur.Models.Entities.Merchant;
import com.ari.majumundur.Models.Response.MerchantResponse;
import com.ari.majumundur.Repository.MerchantRepository;
import com.ari.majumundur.Service.MerchantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;

    @Override
    public Merchant getMerchantById(String merchantId) {
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);


        return merchant.orElseThrow(() -> new EntityNotFoundException("Merchant with ID " + merchantId + " not found"));
    }
}
