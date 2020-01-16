package com.theOne.service;

import com.theOne.domain.User;
import com.theOne.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void saveUser(User user) {
        userMapper.insertSelective(user);
    }

    @Transactional
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional
    public void deleteUser(String id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
