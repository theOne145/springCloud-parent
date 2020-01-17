package com.theOne.client;

import com.theOne.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//fallback 熔断方法
@FeignClient(value = "user-server", fallback = UserClientFallBack.class)
public interface UserClient {

    @GetMapping("{id}")
    User queryUserById(@PathVariable("id") String id);
}
