package com.ari.majumundur.Controller;

import com.ari.majumundur.Constant.AppPath;
import com.ari.majumundur.Models.Entities.Merchant;
import com.ari.majumundur.Models.Request.AuthRequestCustomer;
import com.ari.majumundur.Models.Request.AuthRequestMerchant;
import com.ari.majumundur.Models.Response.CommonResponse;
import com.ari.majumundur.Models.Response.CustomerResponse;
import com.ari.majumundur.Models.Response.MerchantResponse;
import com.ari.majumundur.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppPath.AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody AuthRequestCustomer customerRequest){
        CustomerResponse customerResponse = authService.createCustomer(customerRequest);

        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(String.valueOf(HttpStatus.CREATED.value()))
                .message("Customer Successfully Created")
                .data(customerResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @PostMapping("/register/merchant")
    public ResponseEntity<?> registerMerchant(@RequestBody AuthRequestMerchant merchantRequest){
        MerchantResponse merchantResponse = authService.createMerchant(merchantRequest);

        CommonResponse<MerchantResponse> response = CommonResponse.<MerchantResponse>builder()
                .statusCode(String.valueOf(HttpStatus.CREATED.value()))
                .message("Customer Successfully Created")
                .data(merchantResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
