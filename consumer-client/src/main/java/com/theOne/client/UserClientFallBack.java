package com.theOne.client;

import com.theOne.domain.User;

public class UserClientFallBack implements UserClient {

    @Override
    public User queryUserById(String id) {
        User user = new User();
        user.setRemark("不好意思，服务器太拥挤，稍后尝试");
        return null;
    }
}
