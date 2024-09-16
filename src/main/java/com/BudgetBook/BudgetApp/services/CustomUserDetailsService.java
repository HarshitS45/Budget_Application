package com.BudgetBook.BudgetApp.services;


import com.BudgetBook.BudgetApp.entities.UserEntity;
import com.BudgetBook.BudgetApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByName(username);
        if(userEntity != null){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userEntity.getName())
                    .password(userEntity.getPassword())
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}