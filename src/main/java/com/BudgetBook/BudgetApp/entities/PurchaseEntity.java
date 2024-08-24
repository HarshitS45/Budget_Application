package com.BudgetBook.BudgetApp.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Purchase")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String purchasedItem;
  private Integer cost;
  @CreatedDate
  private LocalDateTime date;

  public void setCost(int cost) {
    this.cost=cost;
  }
}
