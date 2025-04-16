package com.payable_test.visa_dev.utills;

import com.payable_test.visa_dev.exception.ResourceNotFoundException;
import com.payable_test.visa_dev.model.Expense;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ExpenseSummaryUtil {

    public static void summarizeExpenses(List <Expense> expenses) {
        try {

            if (expenses.isEmpty()) {
                throw new ResourceNotFoundException( "Expense list is empty");
            }

            Map<Expense.Category, BigDecimal> totals = expenses.stream()
                    .collect(Collectors.groupingBy(Expense::getCategory,
                            Collectors.reducing(BigDecimal.ZERO, Expense::getAmount, BigDecimal::add)));

            System.out.println("Total spent per category:");
            totals.forEach((k, v) -> System.out.println("- " + k + ": " + v));

            Expense.Category highest = Collections.max(totals.entrySet(), Map.Entry.comparingByValue()).getKey();
            System.out.println("Highest expense category: " + highest);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while summarizing expenses", e);
        }
    }

    public static List<Expense> filterByDateRange(List<Expense> expenses, LocalDate start, LocalDate end) {
        try {
            return expenses.stream()
                    .filter(e -> !e.getDate().isBefore(start.atStartOfDay()) && !e.getDate().isAfter(end.atStartOfDay()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while filtering expenses by date range", e);
        }
    }
}
