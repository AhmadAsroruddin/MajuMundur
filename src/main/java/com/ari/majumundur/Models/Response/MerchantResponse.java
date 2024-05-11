package com.ari.majumundur.Models.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MerchantResponse {
    private String username;
    private String merchantName;
    private String address;
    private String mobilePhone;
    private String email;
}
