package com.theOne.controller;

import com.theOne.domain.User;
import com.theOne.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    @ResponseBody
    public User queryById(@PathVariable("id") String id) {
        return userService.queryById(id);
    }

    @PostMapping("/saveUser")
    public void saveUser(User user) {
        userService.saveUser(user);
    }

    @PutMapping("/updateUser")
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/id")
    public void deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
    }
}
