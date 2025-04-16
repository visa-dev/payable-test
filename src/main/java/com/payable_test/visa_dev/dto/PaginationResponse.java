package com.payable_test.visa_dev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationResponse<T> {
    private String status;
    private String message;
    private T data;
    private int currentPage;
    private int totalPages;
}
