package com.example.demo.controller;

import com.example.demo.model.TransactionType;
import com.example.demo.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class DashboardController {
    private final TransactionService transactionService;

    public DashboardController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        LocalDate now = LocalDate.now();
        model.addAttribute("entradaTotal", transactionService.sumByTypeAndMonth(TransactionType.ENTRADA, now));
        model.addAttribute("saidaTotal", transactionService.sumByTypeAndMonth(TransactionType.SAIDA, now));
        return "dashboard";
    }
}
