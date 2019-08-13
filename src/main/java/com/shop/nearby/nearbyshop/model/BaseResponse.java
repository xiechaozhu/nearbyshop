package com.shop.nearby.nearbyshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse {
    private int status;
    private String msg;
    private Object data;
}
