package com.ari.majumundur.Service;

import com.ari.majumundur.Models.Entities.Merchant;
import com.ari.majumundur.Models.Response.MerchantResponse;

public interface MerchantService {
    Merchant getMerchantById(String merchantId);
}
