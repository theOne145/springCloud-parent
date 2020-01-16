package com.theOne.controller;

import com.theOne.domain.Menu;
import com.theOne.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("{id}")
    @ResponseBody
    public Menu queryById(@PathVariable("id") String id) {
        return menuService.queryById(id);
    }

    @PostMapping("/saveMenu")
    public void saveMenu(Menu menu) {
        menuService.saveMenu(menu);
    }

    @PutMapping("/updateMenu")
    public void updateMenu(Menu menu) {
        menuService.updateMenu(menu);
    }

    @DeleteMapping("/id")
    public void deleteMenu(@PathVariable("id") String id) {
        menuService.deleteMenu(id);
    }
}
