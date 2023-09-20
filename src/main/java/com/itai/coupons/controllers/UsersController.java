package com.itai.coupons.controllers;

import com.itai.coupons.dto.SuccessfulLoginDetails;
import com.itai.coupons.dto.User;
import com.itai.coupons.dto.UserLoginData;
import com.itai.coupons.entities.UserEntity;
import com.itai.coupons.enums.ErrorType;
import com.itai.coupons.exceptions.ApplicationException;
import com.itai.coupons.logic.UsersLogic;
import com.itai.coupons.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersLogic usersLogic;

    @Autowired
    public UsersController(UsersLogic usersLogic) {
        this.usersLogic = usersLogic;
    }

    @PostMapping
    public void addUser(@RequestBody User user) throws ApplicationException {
        usersLogic.addUser(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) throws ApplicationException {
        usersLogic.updateUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() throws ApplicationException {
        List<User> users = usersLogic.getAllUsers();
        return users;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") int userId) throws ApplicationException {
        return usersLogic.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public void removeUser(@PathVariable("userId") int userId) throws ApplicationException {
        usersLogic.removeUser(userId);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginData loginDetails) throws ApplicationException {
        return usersLogic.login(loginDetails);
    }

}
