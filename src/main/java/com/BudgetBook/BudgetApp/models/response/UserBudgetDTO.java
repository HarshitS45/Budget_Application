package com.BudgetBook.BudgetApp.models.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class UserBudgetDTO {

    private String name;
    private String category;
    private Long totalAmount;

    public UserBudgetDTO(String name, String category, Long totalAmount) {
        this.name = name;
        this.category = category;
        this.totalAmount = totalAmount;
    }
}
