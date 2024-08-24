package com.BudgetBook.BudgetApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {

  private Long id;
  private String purchasedItem;
  private Integer cost;
  private LocalDateTime date;
}
