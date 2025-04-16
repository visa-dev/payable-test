package com.payable_test.visa_dev.dto;

import com.payable_test.visa_dev.model.Expense;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequestDTO {

        @NotNull(message = "ID cannot be null")
        private UUID id;

        @NotBlank(message = "Title is mandatory")
        @Size(max = 100, message = "Title cannot be more than 100 characters")
        private String title;

        @NotNull(message = "Amount cannot be null")
        @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
        private BigDecimal amount;

        @NotNull(message = "Category cannot be null")
        private Expense.Category category;

        @NotNull(message = "Date cannot be null")
        private LocalDateTime date;
}


