package com.payable_test.visa_dev.controller;

import com.payable_test.visa_dev.dto.ExpenseRequestDTO;
import com.payable_test.visa_dev.dto.Response;
import com.payable_test.visa_dev.model.Expense;
import com.payable_test.visa_dev.service.ExpenseService;
import com.payable_test.visa_dev.utills.ExpenseSummaryUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Response<?>> add(@RequestBody @Valid ExpenseRequestDTO dto) {

        Expense expense = expenseService.create(dto);

        Response<Expense> response = new Response<>();
        response.setMessage("Expenses created");
        response.setData(expense);
        response.setStatus("success");

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<Response<?>> getAll() {
        List<Expense> expenseList = expenseService.getAll();

        Response<List<Expense>> response = new Response<>();
        response.setMessage("Get all expenses");
        response.setData(expenseList);
        response.setStatus("success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> get(@PathVariable UUID id) {

        Expense expense =  expenseService.getById(id);

        Response<Expense> response = new Response<>();
        response.setMessage("Get expense by id");
        response.setData(expense);
        response.setStatus("success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> update(@PathVariable UUID id, @RequestBody ExpenseRequestDTO dto) {

        Expense expense =  expenseService.update(id, dto);

        Response<Expense> response = new Response<>();
        response.setMessage("Update expense successfully");
        response.setData(expense);
        response.setStatus("success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable UUID id) {
        expenseService.delete(id);

        Response<Expense> response = new Response<>();
        response.setMessage("Delete expense successfully");
        response.setData(null);
        response.setStatus("success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/summery")
    public void summery() {

        List<Expense> expenses = Arrays.asList(
                new Expense(
                        UUID.randomUUID(),
                        "Lunch",
                        new BigDecimal("1500"),
                        Expense.Category.FOOD,
                        LocalDateTime.of(2025, 3, 25, 12, 0)
                ),
                new Expense(
                        UUID.randomUUID(),
                        "Uber Ride",
                        new BigDecimal("2500"),
                        Expense.Category.TRAVEL,
                        LocalDateTime.of(2025, 3, 26, 14, 30)
                ),
                new Expense(
                        UUID.randomUUID(),
                        "Groceries",
                        new BigDecimal("5000"),
                        Expense.Category.FOOD,
                        LocalDateTime.of(2025, 3, 27, 10, 0)
                )
        );
        ExpenseSummaryUtil.summarizeExpenses(expenses);

    }
}
