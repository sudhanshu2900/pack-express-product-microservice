package com.sg.product.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * ErrorResponse class is use to display the error message and its error code
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int code;
    private String message;
}