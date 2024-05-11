package com.ari.majumundur.Controller;

import com.ari.majumundur.Constant.AppPath;
import com.ari.majumundur.Models.Request.OrderRequest;
import com.ari.majumundur.Models.Response.CommonResponse;
import com.ari.majumundur.Models.Response.OrderResponse;
import com.ari.majumundur.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.ORDER)
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.createNewOrder(orderRequest);

        CommonResponse<OrderResponse> response = CommonResponse.<OrderResponse>builder()
                .statusCode(String.valueOf(HttpStatus.CREATED.value()))
                .data(orderResponse)
                .message("ORDER CREATED")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
