package com.BudgetBook.BudgetApp.summary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PurchaseSummary {

  private int totalPurchaseAmount;
  private int averagePurchaseAmount;
  private int totalPurchases;

  public PurchaseSummary(int totalPurchaseAmount, int averagePurchaseAmount, int totalPurchases) {
    this.totalPurchaseAmount = totalPurchaseAmount;
    this.averagePurchaseAmount = averagePurchaseAmount;
    this.totalPurchases = totalPurchases;
  }
}
