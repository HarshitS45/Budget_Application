package com.BudgetBook.BudgetApp.controllers.userController;



import com.BudgetBook.BudgetApp.models.request.UserRequest;
import com.BudgetBook.BudgetApp.models.response.UserResponse;
import com.BudgetBook.BudgetApp.services.CustomUserDetailsService;
import com.BudgetBook.BudgetApp.services.UserService;
import com.BudgetBook.BudgetApp.utilis.JwtUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/public/user")
@RestController
@Slf4j
public class PublicController {

    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody @NotNull @Valid UserRequest userRequest){
        return userService.newUser(userRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @NotNull @Valid UserRequest userRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getName(), userRequest.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(userRequest.getName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch(Exception e){
            log.error("Exception occur while createAuthenticationToken ",e);
            return new ResponseEntity<>("Incorrect Username or Password" ,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
    
}
