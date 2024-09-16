package com.BudgetBook.BudgetApp.services;

import com.BudgetBook.BudgetApp.entities.PurchaseEntity;
import com.BudgetBook.BudgetApp.entities.UserEntity;
import com.BudgetBook.BudgetApp.exceptions.UserNotFoundException;
import com.BudgetBook.BudgetApp.models.request.PurchaseFilterOptions;
import com.BudgetBook.BudgetApp.models.request.PurchaseRequestDTO;
import com.BudgetBook.BudgetApp.models.request.PurchaseUpdateDTO;
import com.BudgetBook.BudgetApp.models.response.PurchaseFilterResponse;
import com.BudgetBook.BudgetApp.models.response.UserBudgetDTO;
import com.BudgetBook.BudgetApp.repositories.PurchaseRepository;
import com.BudgetBook.BudgetApp.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    final PurchaseRepository purchaseRepository;
    final UserRepository userRepository;
    final ModelMapper modelMapper;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public PurchaseRequestDTO newPurchase(PurchaseRequestDTO purchaseRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        PurchaseEntity purchaseEntity = modelMapper.map(purchaseRequestDTO, PurchaseEntity.class);
        UserEntity userentity = userRepository.findByName(name);
        userentity.getPurchaseEntities().add(purchaseEntity);
        purchaseRepository.save(purchaseEntity);
        return purchaseRequestDTO;
    }

    public PurchaseRequestDTO getPurchaseById(Long id) throws UserNotFoundException {
        PurchaseEntity purchaseEntity = purchaseRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (purchaseEntity.isDeleted()) {
            throw new UserNotFoundException("User not found");
        }
        return modelMapper.map(purchaseEntity, PurchaseRequestDTO.class);
    }

    public List<PurchaseRequestDTO> getAllPurchases() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        UserEntity userentity = userRepository.findByName(name);
        List<PurchaseEntity> purchaseEntities = userentity.getPurchaseEntities();
        return purchaseEntities
                .stream().filter(purchaseEntity -> !purchaseEntity.isDeleted())
                .map(purchaseEntity -> modelMapper.map(purchaseEntity, PurchaseRequestDTO.class))
                .collect(Collectors.toList());
    }

    public String deletePurchaseById(Long id) {
        boolean isPresent = purchaseRepository.existsById(id);
        if (!isPresent)
            return "Not Found";
        PurchaseEntity purchaseEntity = purchaseRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        purchaseEntity.setDeleted(true);
        purchaseRepository.save(purchaseEntity);
        return "Removed";
    }

    public List<PurchaseRequestDTO> getAllDeletedPurchases() {
        return purchaseRepository
                .findAll()
                .stream().filter(PurchaseEntity::isDeleted)
                .map(purchaseEntity -> modelMapper.map(purchaseEntity, PurchaseRequestDTO.class))
                .collect(Collectors.toList());
    }

    public String purchaseReinstate(Long id) throws UserNotFoundException {
        PurchaseEntity purchaseEntity = purchaseRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (purchaseEntity.isDeleted()) {
            purchaseEntity.setDeleted(false);
            purchaseRepository.save(purchaseEntity);
            return "Restored";
        } else {
            return "Already in repository";
        }
    }

    public String emptyTrash() {
        List<PurchaseEntity> entities = purchaseRepository.findAll()
                .stream().filter(PurchaseEntity::isDeleted).toList();
        if (entities.isEmpty()) {
            return "Trash is Empty";
        }
        for (PurchaseEntity entity : entities) {
            purchaseRepository.deleteById(entity.getTransactionId());
        }
        return "Done";
    }

    public String updatePurchase(Long id, PurchaseUpdateDTO requestDTO) throws UserNotFoundException {
        try {
            PurchaseEntity purchaseEntity = purchaseRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
            if (requestDTO.getAmount() != null) {
                purchaseEntity.setAmount(requestDTO.getAmount());
                purchaseRepository.save(purchaseEntity);
            }
            if (requestDTO.getReason() != null) {
                purchaseEntity.setReason(requestDTO.getReason());
                purchaseRepository.save(purchaseEntity);
            }
            if (requestDTO.getDate() != null) {
                purchaseEntity.setDate(requestDTO.getDate());
                purchaseRepository.save(purchaseEntity);
            }
            return "Updated Successfully";
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User not found");
        }
    }

    public List<PurchaseRequestDTO> getPurchaseSummaryByDays(Integer days) {
        List<PurchaseEntity> purchaseEntityList = purchaseRepository
                .getPurchaseSummaryFromDate(LocalDateTime.now().minusDays(days));
        List<PurchaseRequestDTO> purchaseRequestDTOList = new ArrayList<>();
        for (PurchaseEntity purchaseEntity : purchaseEntityList) {
            purchaseRequestDTOList.add(modelMapper.map(purchaseEntity, PurchaseRequestDTO.class));
        }
        return purchaseRequestDTOList;
    }

    public List<PurchaseRequestDTO> getPurchaseSummaryByHours(Integer hours) {
        List<PurchaseEntity> purchaseEntityList = purchaseRepository
                .getPurchaseSummaryFromDate(LocalDateTime.now().minusHours(hours));
        List<PurchaseRequestDTO> purchaseRequestDTOList = new ArrayList<>();
        for (PurchaseEntity purchaseEntity : purchaseEntityList) {
            purchaseRequestDTOList.add(modelMapper.map(purchaseEntity, PurchaseRequestDTO.class));
        }
        return purchaseRequestDTOList;
    }

    public List<PurchaseRequestDTO> getPurchaseOfTimePeriod(LocalDate startDate, LocalDate endDate) {
        List<PurchaseEntity> purchaseEntityList = purchaseRepository.getPurchaseOfTimePeriod(startDate, endDate);
        List<PurchaseRequestDTO> purchaseRequestDTOList = new ArrayList<>();
        for (PurchaseEntity purchaseEntity : purchaseEntityList) {
            purchaseRequestDTOList.add(modelMapper.map(purchaseEntity, PurchaseRequestDTO.class));
        }
        return purchaseRequestDTOList;
    }

    public List<PurchaseFilterResponse> getPurchaseSummaryByCategory(PurchaseFilterOptions requestDTO) {
        return purchaseRepository.getSummary(requestDTO.getCategory(), requestDTO.getModeOfPayment());
    }

    public List<UserBudgetDTO> findUserBudgetSummary(Long id){
        return purchaseRepository.findUserBudgetSummary(id);
    }

}
