package com.BudgetBook.BudgetApp.models.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PurchaseUpdateDTO {

    private Integer amount;
    private String reason;
    private LocalDateTime date;
}
