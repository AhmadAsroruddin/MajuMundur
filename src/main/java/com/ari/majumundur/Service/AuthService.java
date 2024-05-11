package com.ari.majumundur.Service;

import com.ari.majumundur.Models.Request.AuthRequestCustomer;
import com.ari.majumundur.Models.Request.AuthRequestMerchant;
import com.ari.majumundur.Models.Response.CustomerResponse;
import com.ari.majumundur.Models.Response.MerchantResponse;

public interface AuthService {
    CustomerResponse createCustomer (AuthRequestCustomer customerRequest);
    MerchantResponse createMerchant (AuthRequestMerchant merchantRequest);

}
