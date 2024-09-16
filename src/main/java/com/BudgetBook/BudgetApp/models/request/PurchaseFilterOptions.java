package com.BudgetBook.BudgetApp.models.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PurchaseFilterOptions {
    private LocalDate startDate;
    private LocalDate endDate;
    private String category;
    private String modeOfPayment;
}
