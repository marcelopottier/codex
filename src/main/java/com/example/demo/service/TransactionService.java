package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionType;
import com.example.demo.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> findAll() {
        return repository.findAll();
    }

    public Transaction save(Transaction t) {
        return repository.save(t);
    }

    public Transaction getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public BigDecimal sumByTypeAndMonth(TransactionType type, LocalDate month) {
        LocalDate start = month.withDayOfMonth(1);
        LocalDate end = start.plusMonths(1);
        return repository.findAll().stream()
                .filter(t -> t.getType() == type && (t.getDate().isEqual(start) || t.getDate().isAfter(start)) && t.getDate().isBefore(end))
                .map(Transaction::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
