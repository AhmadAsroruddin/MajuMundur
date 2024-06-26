package com.ari.majumundur.Models.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class MerchantResponse {
    private String username;
    private String merchantName;
    private String address;
    private String mobilePhone;
    private String email;
}
