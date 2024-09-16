package com.BudgetBook.BudgetApp.entities;

import com.BudgetBook.BudgetApp.models.request.PurchaseRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String name;
    private boolean isDeleted;
    private String password;
    private Long phoneNo;
    @OneToMany(targetEntity = PurchaseEntity.class)
    @JoinColumn(name = "user_id")
    private List<PurchaseEntity> purchaseEntities;


    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                ", phoneNo=" + phoneNo +
                ", purchaseEntities=" + purchaseEntities.size() +
                '}';
    }
}
