package com.BudgetBook.BudgetApp.models.response;

import com.BudgetBook.BudgetApp.entities.PurchaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserResponse {
    String name;
    Long phoneNo;
    private List<PurchaseEntity> purchaseEntities;
}

