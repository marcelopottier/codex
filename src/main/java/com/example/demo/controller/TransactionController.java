package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.model.Category;
import com.example.demo.model.TransactionType;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService service;
    private final CategoryRepository categoryRepository;

    public TransactionController(TransactionService service, CategoryRepository categoryRepository) {
        this.service = service;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("transactions", service.findAll());
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("types", TransactionType.values());
        return "transactions";
    }

    @PostMapping
    public String save(@ModelAttribute Transaction transaction) {
        service.save(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/transactions";
    }
}
