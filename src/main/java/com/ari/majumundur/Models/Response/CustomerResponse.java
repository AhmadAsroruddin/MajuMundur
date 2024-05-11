package com.ari.majumundur.Models.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class CustomerResponse {
    private String username;
    private String name;
    private Integer points;
    private String mobile_phone;
    private String email;
    private String address;
}
