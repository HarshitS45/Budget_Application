package com.BudgetBook.BudgetApp.controllers;

import com.BudgetBook.BudgetApp.dto.PurchaseDTO;
import com.BudgetBook.BudgetApp.entities.PurchaseEntity;
import com.BudgetBook.BudgetApp.services.PurchaseService;
import com.BudgetBook.BudgetApp.summary.PurchaseSummary;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping(path = "/purchases")
@RestController
public class PurchaseController {

  private final PurchaseService purchaseService;

  public PurchaseController(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  @GetMapping(path = "/{id}")
  public PurchaseDTO getPurchaseById(@PathVariable("id") Long id){
    return purchaseService.getPurchaseById(id);
  }

  @GetMapping
  public List<PurchaseDTO> getAllPurchases(){
    return purchaseService.getAllPurchases();
  }

  @PostMapping
  public PurchaseDTO newPurchase(@RequestBody PurchaseDTO purchaseDTO){
    return purchaseService.newPurchase(purchaseDTO);
  }

  @DeleteMapping(path = "/{id}")
  public String deletePurchaseById(@PathVariable("id") Long id){
    return purchaseService.deletePurchaseById(id);
  }

  @PatchMapping("/{id}/cost")
  public String updateAmount(@PathVariable Long id, @RequestBody int cost) {
      return purchaseService.updateAmount(id,cost);
  }

//  @GetMapping("/summary")
//  public PurchaseSummary getPurchaseSummary(){
//    return purchaseService.getPurchaseSummary();
//  }
//
//  @GetMapping("/week")
//  public int getSpentThisWeek(){
//    return purchaseService.getSpentThisWeek();
//  }
//
//  @GetMapping("/month")
//  public int getSpentThisMonth(){
//    return purchaseService.getSpentThisMonth();
//  }
//
//  @GetMapping("/yesterday")
//  public int getSpentYesterday(){
//    return purchaseService.getSpentYesterday();
//  }
//
//  @GetMapping("/timePeriod")
//  public int getSpentYesterday(@RequestParam("startDate") LocalDateTime startDate , @RequestParam("endDate") LocalDateTime endDate){
//    return purchaseService.getSpentOfTimePeriod(startDate, endDate);
//  }

}
