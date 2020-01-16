package com.theOne.service;

import com.theOne.domain.Roles;
import com.theOne.domain.User;
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
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Test
    public void queryById() {
        User user = userService.queryById("1");
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        Date now = new Date();
        User user = new User();
        user.setId("11");
        user.setUserName("zx");
        user.setUserPwd("123");
        user.setUserPhone("15110280804");
        user.setCreateTime(now);
        user.setModifyTime(now);
        user.setRemark("insert");
        userService.saveUser(user);

    }

    @Test
    public void updateUser() {
        Date now = new Date();
        User user = new User();
        user.setId("11");
        user.setUserName("zx4");
        user.setUserPwd("1234");
        user.setUserPhone("15110280804");
        user.setCreateTime(now);
        user.setModifyTime(now);
        user.setRemark("update");
        userService.updateUser(user);
    }

    @Test
    public void deleteUser() {
        userService.deleteUser("11");
    }

    @Test
    public void updateRoles() {
        Date createTime = new Date();
        Date modifyTime = new Date();
        String uid = "1";
        String rid[] = {"1", "2"};
        String remark ="为用户添加角色";
        roleService.insertUserRole(uid,rid,createTime,modifyTime,remark);
    }
    
    @Test
    public void findRolesByUserId(){
        String uid = "1";
        List<Roles> roles = roleService.findRolesByUserId(uid);
        for (int i = 0; i < roles.size(); i++) {
            System.out.println(roles.get(i));
        }
    }
}