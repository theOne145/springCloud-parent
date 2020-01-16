package com.theOne.service;

import com.theOne.domain.Menu;
import com.theOne.domain.Roles;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@MapperScan("com.theOne.mapper")
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Test
    public void queryById() {
        Roles role = roleService.queryById("1");
        System.out.println(role);

    }

    @Test
    public void saveRole() {
        Date now = new Date();
        Roles role = new Roles();
        role.setId("2");
        role.setRoleName("管理员");
        role.setCreateTime(now);
        role.setModifyTime(now);
        role.setRemark("添加管理员");
        roleService.saveRole(role);
    }

    @Test
    public void updateRole() {
        Date now = new Date();
        Roles role = new Roles();
        role.setId("1");
        role.setCreateTime(now);
        role.setModifyTime(now);
        roleService.updateRole(role);
    }

    @Test
    public void deleteRole() {
        roleService.deleteRole("2");
    }

    @Test
    public void insertRoleMenu() {
        String rid = "1";
        String mid[] = {"1", "2"};
        Date createTime = new Date();
        Date modifyTime = new Date();
        String remark = "添加菜单栏";
        menuService.insertRoleMenu(rid, mid, createTime, modifyTime, remark);
    }

    @Test
    public void findMenuByRoleId() {
        String rid = "1";
        List<Menu> menus = menuService.findMenuByRoleId(rid);
        for (int i = 0; i < menus.size(); i++) {
            System.out.println(menus.get(i));
        }
    }
}