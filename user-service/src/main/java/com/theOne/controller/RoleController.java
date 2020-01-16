package com.theOne.controller;

import com.theOne.domain.Roles;
import com.theOne.mapper.RolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RolesMapper RolesMapper;

    @GetMapping("{id}")
    @ResponseBody
    public Roles queryById(@PathVariable("id") String id) {
        return RolesMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/saveRole")
    public void saveRole(Roles role) {
        RolesMapper.insertSelective(role);
    }

    @PutMapping("/updateRole")
    public void updateRole(Roles role) {
        RolesMapper.updateByPrimaryKey(role);
    }

    @DeleteMapping("/id")
    public void deleteRole(@PathVariable("id") String id) {
        RolesMapper.deleteByPrimaryKey(id);
    }
}
