package com.BudgetBook.BudgetApp.models.response;

import lombok.Data;

@Data
public class PurchaseFilterResponse {

    private Long count;
    private Long sum ;
    private String category;
    private String modeOfPayment;

    public PurchaseFilterResponse(Long sum, Long count, String category, String modeOfPayment) {
        this.count = count;
        this.sum = sum;
        this.category = category;
        this.modeOfPayment = modeOfPayment;
    }
}
