package com.BudgetBook.BudgetApp.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRequest {
    String name;
    String password;
    Long phoneNo;
}
