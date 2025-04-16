package com.payable_test.visa_dev.service;

import com.payable_test.visa_dev.dto.ExpenseRequestDTO;
import com.payable_test.visa_dev.exception.ResourceNotFoundException;
import com.payable_test.visa_dev.model.Expense;
import com.payable_test.visa_dev.repo.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository repository;

    public List<Expense> getAll() {
        return repository.findAll();
    }

    public Expense getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with ID: " + id));
    }

    @Transactional
    public Expense create(ExpenseRequestDTO expenseRequestDTO) {
        Expense expense = new Expense();
        expense.setId(expenseRequestDTO.getId());
        expense.setAmount(expenseRequestDTO.getAmount());
        expense.setTitle(expenseRequestDTO.getTitle());
        expense.setCategory(expenseRequestDTO.getCategory());
        expense.setDate(expenseRequestDTO.getDate());


        return repository.save(expense);
    }

    @Transactional
    public Expense update(UUID id, ExpenseRequestDTO expenseRequestDTO) {

        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with ID: " + id));

        Expense expense = getById(id);

        expense.setTitle(expenseRequestDTO.getTitle());
        expense.setAmount(expenseRequestDTO.getAmount());
        expense.setCategory(expenseRequestDTO.getCategory());
        expense.setDate(expenseRequestDTO.getDate());

        return repository.save(expense);
    }

    public void delete(UUID id) {

        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with ID: " + id));

        repository.deleteById(id);
    }

}
