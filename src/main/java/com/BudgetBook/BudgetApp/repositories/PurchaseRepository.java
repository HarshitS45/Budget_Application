package com.BudgetBook.BudgetApp.repositories;

import com.BudgetBook.BudgetApp.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity,Long>{

//  @Query(value = "SELECT SUM(cost) FROM Purchase", nativeQuery = true)
//  int getTotalPurchaseAmount();
//
//  @Query(value = "SELECT AVG(cost) FROM Purchase", nativeQuery = true)
//  int getAveragePurchase();
//
//  @Query(value = "SELECT count(cost) FROM Purchase", nativeQuery = true)
//  int getTotalPurchases();
//
//  @Query(value = "SELECT SUM(cost) FROM Purchase WHERE YEAR(date) = YEAR(CURRENT_DATE) AND WEEK(date) = WEEK(CURRENT_DATE)" ,nativeQuery = true)
//  int getSpentThisWeek();
//
//  @Query(value = "SELECT SUM(cost) FROM Purchase WHERE YEAR(date) = YEAR(CURRENT_DATE) AND MONTH(date) = MONTH(CURRENT_DATE)" ,nativeQuery = true)
//  int getSpentThisMonth();
//
//  @Query(value = "SELECT SUM(cost) FROM Purchase WHERE DATE(date) = DATE(CURRENT_DATE)" ,nativeQuery = true)
//  int getSpentYesterday();
//
//  @Query(value="SELECT SUM(cost) FROM Purchase WHERE date BETWEEN :startDate AND :endDate",nativeQuery = true)
//  int getSpentOfTimePeriod(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
