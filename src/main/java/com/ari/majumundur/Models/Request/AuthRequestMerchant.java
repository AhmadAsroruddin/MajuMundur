package com.ari.majumundur.Models.Request;

import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AuthRequestMerchant {
    private String username;
    private String password;
    private String merchantName;
    private String address;
    private String mobile_phone;
    private String email;
}
