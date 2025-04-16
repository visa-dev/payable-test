package com.payable_test.visa_dev.dto;

import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private String status;
    private String message;
    private T data;
}
