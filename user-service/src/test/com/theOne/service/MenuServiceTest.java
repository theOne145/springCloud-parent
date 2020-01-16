package com.theOne.service;

import com.theOne.domain.Menu;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@MapperScan("com.theOne.mapper")
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void queryById() {
        Menu menu = menuService.queryById("1");
    }

    @Test
    public void saveMenu() {
        Date now = new Date();
        Menu menu = new Menu ();
        menu.setId("3");
        menu.setMenuName("角色管理");
        menu.setUrl("role/");
        menu.setParentId("0");
        menu.setCreateTime(now);
        menu.setModifyTime(now);
        menu.setRemark("添加menu");
        menuService.saveMenu(menu);
    }

    @Test
    public void updateMenu() {
        Menu menu = new Menu();
        menu.setId("2");
        menu.setRemark("修改menu");
        menuService.updateMenu(menu);
    }

    @Test
    public void deleteMenu() {
        menuService.deleteMenu("3");
    }
}