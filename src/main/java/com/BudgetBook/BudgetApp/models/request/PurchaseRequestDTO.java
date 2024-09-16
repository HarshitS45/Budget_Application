package com.BudgetBook.BudgetApp.models.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequestDTO {

    @Min(1)
    private Integer amount;
    @NotBlank
    private String modeOfPayment;
    @NotBlank
    private String category;
    @NotBlank
    private String reason;
    private LocalDateTime date = LocalDateTime.now();

}
