package com.ari.majumundur.Controller;

import com.ari.majumundur.Constant.AppPath;
import com.ari.majumundur.Models.Request.ProductRequest;
import com.ari.majumundur.Models.Response.CommonResponse;
import com.ari.majumundur.Models.Response.ProductResponse;
import com.ari.majumundur.Service.ProductPriceService;
import com.ari.majumundur.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(AppPath.PRODUCT)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductPriceService productPriceService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.createProductAndPrice(productRequest);

        CommonResponse<ProductResponse> response = CommonResponse.<ProductResponse>builder()
                .statusCode(String.valueOf(HttpStatus.CREATED.value()))
                .message("Product Created Successfully")
                .data(productResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> createProduct(@Valid @PathVariable String id, @RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.update(id,productRequest);

        CommonResponse<ProductResponse> response = CommonResponse.<ProductResponse>builder()
                .statusCode(String.valueOf(HttpStatus.OK.value()))
                .message("Product Updated Successfully")
                .data(productResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/browseAllProduct")
    public ResponseEntity<?> getAllProduct(){
        List<ProductResponse> productResponseList = productPriceService.getAllProduct();

        CommonResponse<List<ProductResponse>> response = CommonResponse.<List<ProductResponse>>builder()
                .statusCode(String.valueOf(HttpStatus.OK.value()))
                .message("All Product")
                .data(productResponseList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
