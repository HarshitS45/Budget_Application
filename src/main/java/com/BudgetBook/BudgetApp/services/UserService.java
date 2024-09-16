package com.BudgetBook.BudgetApp.services;

import com.BudgetBook.BudgetApp.entities.PurchaseEntity;
import com.BudgetBook.BudgetApp.entities.UserEntity;
import com.BudgetBook.BudgetApp.exceptions.UserNotFoundException;
import com.BudgetBook.BudgetApp.models.request.PurchaseUpdateDTO;
import com.BudgetBook.BudgetApp.models.request.UserRequest;
import com.BudgetBook.BudgetApp.models.response.UserResponse;
import com.BudgetBook.BudgetApp.repositories.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserResponse newUser(UserRequest userRequest) {
        UserEntity userentity = modelMapper.map(userRequest,UserEntity.class);
        userentity.setPassword(passwordEncoder.encode(userentity.getPassword()));
        userRepository.save(userentity);
        return modelMapper.map(userRequest,UserResponse.class);
    }

    public UserResponse getUserDetails() throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        UserEntity userentity = userRepository.findByName(name);
        return modelMapper.map(userentity,UserResponse.class);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userEntity -> modelMapper.map(userEntity,UserResponse.class)).toList();
    }

    public String deleteUser(Long id) {
        boolean isPresent = userRepository.existsById(id);
        if (!isPresent)
            return "Not Found";
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userEntity.setDeleted(true);
        userRepository.save(userEntity);
        return "Done";
    }

    public String updateUser(UserRequest userRequest) throws UserNotFoundException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        UserEntity userEntity = userRepository.findByName(name);
        if (userRequest.getName() != null) {
            userEntity.setName(userRequest.getName());
            userRepository.save(userEntity);
        }
        if (userRequest.getPassword() != null) {
            userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            userRepository.save(userEntity);
        }
        if (userRequest.getPhoneNo() != null) {
            userEntity.setPhoneNo(userRequest.getPhoneNo());
            userRepository.save(userEntity);
        }
        return "Updated Successfully";
    }

    public UserResponse login(@NotNull @Valid UserRequest userRequest) {
        UserEntity userentity = modelMapper.map(userRequest,UserEntity.class);
        userentity.setPassword(passwordEncoder.encode(userentity.getPassword()));
        userRepository.save(userentity);
        return modelMapper.map(userRequest,UserResponse.class);
    }
}
