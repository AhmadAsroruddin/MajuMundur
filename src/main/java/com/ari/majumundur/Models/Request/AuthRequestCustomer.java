package com.ari.majumundur.Models.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AuthRequestCustomer {
    private String username;
    private String password;
    private String name;
    private String mobilePhone;
    private String email;
    private String address;
}

