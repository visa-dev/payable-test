package com.payable_test.visa_dev.dto;

import com.payable_test.visa_dev.model.Expense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponseDTO {
    private UUID id;
    private String title;
    private BigDecimal amount;
    private Expense.Category category;
    private LocalDateTime date;
}
