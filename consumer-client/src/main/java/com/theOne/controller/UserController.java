package com.theOne.controller;

import com.theOne.client.UserClient;
import com.theOne.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("consumer")
//@DefaultProperties(defaultFallback = "defaultFallBack")
public class UserController {

    /*@Autowired
    private RestTemplate restTemplate;*/

    @Autowired
    private UserClient userClient;

    @GetMapping("{id}")
    /*@HystrixCommand (commandProperties = {
             @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    @HystrixCommand
    */
    public User queryUserById(@PathVariable("id") String id) {
        /**
         * 方案一 、
         * timeoutInMilliseconds 熔断(Hystrix)超时时长设置为3秒
         * ribbon（） +  eureKa + restTemplate
        String url = "http://user-server/user/" + id;
        User user = restTemplate.getForObject(url,User.class);
         */

        /**
         * =====================================================
         */

        /**
         * 最终方案 ：Feign
         * Feign = eureka + ribbon + restTemplate + Hystrix
         */
        User user = userClient.queryUserById(id);
        return user;
    }

    /*public String defaultFallBack() {
        return "不好意思，服务器太拥挤，稍后尝试";
    }*/
}
