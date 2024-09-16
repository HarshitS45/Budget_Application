package com.BudgetBook.BudgetApp.controllers.userController;

import com.BudgetBook.BudgetApp.exceptions.UserNotFoundException;
import com.BudgetBook.BudgetApp.models.request.UserRequest;
import com.BudgetBook.BudgetApp.models.response.UserResponse;
import com.BudgetBook.BudgetApp.services.UserService;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/private/user")
@RestController
public class PrivateController {

    private final UserService userService;

    public PrivateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userDetail")
    public UserResponse getUserDetails() throws UserNotFoundException {
        return userService.getUserDetails();
    }

    @PatchMapping(path = "/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id){
        return userService.deleteUser(id);
    }

    @PatchMapping("/updateUser")
    public String updateUser(@RequestBody UserRequest userRequest) throws UserNotFoundException {
        return userService.updateUser(userRequest);
    }
}
