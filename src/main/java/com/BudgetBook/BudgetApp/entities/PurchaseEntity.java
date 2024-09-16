package com.BudgetBook.BudgetApp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "budgetbook")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) @Column(updatable = false)
  private Long transactionId;

  private Integer amount;

  @Column(updatable = false)
  private String modeOfPayment;

  @Column(updatable = false)
  private String category;

  @Size(min = 10, max = 50)
  private String reason;

  private LocalDateTime date;

  private boolean isDeleted;

  @CreatedDate
  private LocalDateTime created;

  @LastModifiedDate
  private LocalDateTime updated;

  //private Long userId;

//  @ManyToOne(targetEntity = UserEntity.class)
//  @JoinColumn(name = "user_id")
//  private UserEntity userEntity;

}
