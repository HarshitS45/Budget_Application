package com.BudgetBook.BudgetApp.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseSummary {

  private int totalPurchaseAmount;
  private int averagePurchaseAmount;
  private int totalPurchases;

}
