package com.theOne.controller;

import com.theOne.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("consumer")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("{id}")
    public User queryUserById(@PathVariable("id") String id) {
        String url = "http://user-server/user/" + id;
        User user = restTemplate.getForObject(url,User.class);
        return user;
    }
}
