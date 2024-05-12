package com.ari.majumundur.Controller;

import com.ari.majumundur.Constant.AppPath;
import com.ari.majumundur.Models.Entities.Customer;
import com.ari.majumundur.Models.Response.CustomerBuyer;
import com.ari.majumundur.Models.Response.CustomerResponse;
import com.ari.majumundur.Service.OrderDetailService;
import com.ari.majumundur.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final OrderService orderService;
    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity<?> getCustomer(@PathVariable String merchantId){
        List<CustomerBuyer> customerResponses = orderService.findCustomersByStore(merchantId);

        return ResponseEntity.status(HttpStatus.OK).body(customerResponses);
    }
}
