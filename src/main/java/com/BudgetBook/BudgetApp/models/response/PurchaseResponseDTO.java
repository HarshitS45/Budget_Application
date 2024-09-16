package com.BudgetBook.BudgetApp.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponseDTO {
    private Long transactionId;
    private String category;
    private String modeOfPayment;
    private Integer amount;
    private String reason;
    private LocalDateTime date;
    private boolean isDeleted;
    private LocalDateTime created;
    private LocalDateTime updated;
}
