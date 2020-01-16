package com.theOne.service;

import com.theOne.domain.Roles;
import com.theOne.mapper.RolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class RoleService {

    @Autowired
    private RolesMapper rolesMapper;

    public Roles queryById(String id) {
        return rolesMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void saveRole(Roles role) {
        rolesMapper.insertSelective(role);
    }

    @Transactional
    public void updateRole(Roles role) {
        rolesMapper.updateByPrimaryKeySelective(role);
    }

    @Transactional
    public void deleteRole(String id) {
        rolesMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void insertUserRole(String uid, String[] rid, Date createTime, Date modifyTime, String remark) {
        for (int i = 0; i < rid.length; i++) {
            rolesMapper.insertUserRole(uid, rid[i], createTime, modifyTime, remark);
        }
    }

    public List<Roles> findRolesByUserId(String uid) {
        return rolesMapper.findRolesByUserId(uid);
    }
}
