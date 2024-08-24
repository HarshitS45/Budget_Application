package com.BudgetBook.BudgetApp.services;

import com.BudgetBook.BudgetApp.dto.PurchaseDTO;
import com.BudgetBook.BudgetApp.entities.PurchaseEntity;
import com.BudgetBook.BudgetApp.repositories.PurchaseRepository;
import com.BudgetBook.BudgetApp.summary.PurchaseSummary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

  final PurchaseRepository purchaseRepository;

  final ModelMapper modelMapper;

  public PurchaseService(PurchaseRepository purchaseRepository, ModelMapper modelMapper) {
    this.purchaseRepository = purchaseRepository;
    this.modelMapper = modelMapper;
  }

  public PurchaseDTO getPurchaseById(Long id){
    PurchaseEntity purchaseEntity = purchaseRepository.findById(id).orElse(null);
    return modelMapper.map(purchaseEntity,PurchaseDTO.class);
  }

  public PurchaseDTO newPurchase(PurchaseDTO purchaseDTO) {
    PurchaseEntity purchaseEntity = modelMapper.map(purchaseDTO,PurchaseEntity.class);
    purchaseRepository.save(purchaseEntity);
    return purchaseDTO;
  }

  public List<PurchaseDTO> getAllPurchases() {
    return purchaseRepository
        .findAll()
        .stream()
        .map(purchaseEntity -> modelMapper.map(purchaseEntity,PurchaseDTO.class))
        .collect(Collectors.toList());
  }

  public String deletePurchaseById(Long id) {
    boolean isPresent = purchaseRepository.existsById(id);
    if(!isPresent)
      return "Not Found";
    purchaseRepository.deleteById(id);
    return "Removed";
  }

  public String updateAmount(Long id, int cost) {
    PurchaseEntity purchaseEntity = purchaseRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    purchaseEntity.setCost(cost);
    purchaseRepository.save(purchaseEntity);
    return "Successfully updated";
  }

//  public PurchaseSummary getPurchaseSummary() {
//    int totalPurchaseAmount=purchaseRepository.getTotalPurchaseAmount();
//    int averagePurchase=purchaseRepository.getAveragePurchase();
//    int totalPurchases=purchaseRepository.getTotalPurchases();
//    PurchaseSummary purchaseSummary;
//    return purchaseSummary = new PurchaseSummary(totalPurchaseAmount,averagePurchase,totalPurchases);
//  }
//
//  public int getSpentThisWeek(){
//    return purchaseRepository.getSpentThisWeek();
//  }
//
//  public int getSpentThisMonth(){
//    return purchaseRepository.getSpentThisMonth();
//  }
//
//  public int getSpentYesterday() {
//    return purchaseRepository.getSpentYesterday();
//  }
//
//  public int getSpentOfTimePeriod(LocalDateTime startDate ,LocalDateTime endDate){
//    return purchaseRepository.getSpentOfTimePeriod(startDate,endDate);
//  }
//public int getSpentOfTimePeriod(LocalDateTime startDate ,LocalDateTime endDate){
//    return purchaseRepository.getSpentOfTimePeriod(startDate,endDate);
//  }

}
