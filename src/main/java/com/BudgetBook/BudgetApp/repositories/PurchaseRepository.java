package com.BudgetBook.BudgetApp.repositories;

import com.BudgetBook.BudgetApp.entities.PurchaseEntity;
import com.BudgetBook.BudgetApp.models.response.PurchaseFilterResponse;
import com.BudgetBook.BudgetApp.models.response.UserBudgetDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    @Query(value = "select * from budgetbook where date between :startDate and :endDate", nativeQuery = true)
    List<PurchaseEntity> getPurchaseOfTimePeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "from PurchaseEntity where date > :fromDateTime")
    List<PurchaseEntity> getPurchaseSummaryFromDate(@Param("fromDateTime") LocalDateTime fromDateTime);

    @Query(value = "select " +
            "new com.BudgetBook.BudgetApp.models.response.PurchaseFilterResponse " +
            "(sum(amount),count(transactionId), category, modeOfPayment) " +
            "from PurchaseEntity where category = :category and modeOfPayment = :modeOfPayment " +
            "group by category, modeOfPayment")
    List<PurchaseFilterResponse> getSummary(
            @Param("category") String category,
            @Param("modeOfPayment") String modeOfPayment);

    @Query(value = "select " +
            "new com.BudgetBook.BudgetApp.models.response.UserBudgetDTO " +
            "(u.name, b.category, SUM(b.amount)) " +
            "FROM UserEntity u JOIN u.purchaseEntities b " +
            "WHERE u.userId = :userId " +
            "GROUP BY u.name, b.category")
    List<UserBudgetDTO> findUserBudgetSummary(
            @Param("userId") Long userId);
}
