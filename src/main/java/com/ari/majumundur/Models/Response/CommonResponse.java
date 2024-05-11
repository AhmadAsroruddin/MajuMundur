package com.ari.majumundur.Models.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommonResponse<T> {
    private String statusCode;
    private String message;
    private T data;
}
