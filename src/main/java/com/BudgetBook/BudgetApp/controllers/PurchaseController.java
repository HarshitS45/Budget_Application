package com.BudgetBook.BudgetApp.controllers;

import com.BudgetBook.BudgetApp.exceptions.UserNotFoundException;
import com.BudgetBook.BudgetApp.models.request.PurchaseFilterOptions;
import com.BudgetBook.BudgetApp.models.request.PurchaseRequestDTO;
import com.BudgetBook.BudgetApp.models.request.PurchaseUpdateDTO;
import com.BudgetBook.BudgetApp.models.response.PurchaseFilterResponse;
import com.BudgetBook.BudgetApp.models.response.UserBudgetDTO;
import com.BudgetBook.BudgetApp.services.PurchaseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping(path = "/purchases")
@RestController
public class PurchaseController {

  private final PurchaseService purchaseService;

  public PurchaseController(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  @PostMapping
  public PurchaseRequestDTO newPurchase(@RequestBody @NotNull @Valid PurchaseRequestDTO purchaseRequestDTO){
    return purchaseService.newPurchase(purchaseRequestDTO);
  }

  @GetMapping(path = "/{id}")
  public PurchaseRequestDTO getPurchaseById(@PathVariable("id") Long id) throws UserNotFoundException {
    return purchaseService.getPurchaseById(id);
  }

  @GetMapping
  public List<PurchaseRequestDTO> getAllPurchases(){
    return purchaseService.getAllPurchases();
  }

  @PatchMapping(path = "/{id}/delete")
  public String deletePurchaseById(@PathVariable("id") Long id){
    return purchaseService.deletePurchaseById(id);
  }

  @GetMapping(path = "/trash")
  public List<PurchaseRequestDTO> getAllDeletedPurchases(){
    return purchaseService.getAllDeletedPurchases();
  }

  @PatchMapping(path = "/{id}/reinstate")
  public String purchaseReinstate(@PathVariable("id") Long id) throws UserNotFoundException {
    return purchaseService.purchaseReinstate(id);
  }

  @DeleteMapping(path = "/emptyTrash")
  public String emptyTrash(){
    return purchaseService.emptyTrash();
  }

  @PatchMapping("/{id}/updatePurchase")
  public String updatePurchase(@PathVariable Long id, @RequestBody PurchaseUpdateDTO requestDTO) throws UserNotFoundException {
    return purchaseService.updatePurchase(id,requestDTO);
  }

  @GetMapping("/{days}/summaryByDays")
  public List<PurchaseRequestDTO> getPurchaseSummaryByDays(@PathVariable Integer days){
    return purchaseService.getPurchaseSummaryByDays(days);
  }

  @GetMapping("/summaryByCategory")
  public List<PurchaseFilterResponse> getPurchaseSummaryByCategory(@RequestBody PurchaseFilterOptions requestDTO){
    return purchaseService.getPurchaseSummaryByCategory(requestDTO);
  }

  @GetMapping("/purchaseOfTimePeriod")
  public List<PurchaseRequestDTO> getPurchaseOfTimePeriod(@RequestParam("startDate") LocalDate startDate , @RequestParam("endDate") LocalDate endDate){
    return purchaseService.getPurchaseOfTimePeriod(startDate, endDate);
  }

  @GetMapping("/user-budget/{id}")
  public List<UserBudgetDTO> findUserBudgetSummary(@PathVariable Long id){
    return purchaseService.findUserBudgetSummary(id);
  }

}


