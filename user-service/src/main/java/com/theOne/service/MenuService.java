package com.theOne.service;

import com.theOne.domain.Menu;
import com.theOne.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public Menu queryById(String id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void saveMenu(Menu menu) {
        menuMapper.insertSelective(menu);
    }

    @Transactional
    public void updateMenu(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Transactional
    public void deleteMenu(String id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void insertRoleMenu(String rid, String[] mid, Date createTime, Date modifyTime, String remark) {
        for (int i = 0; i < mid.length; i++) {
            menuMapper.insertRoleMenu(rid, mid[i], createTime, modifyTime, remark);
        }
    }

    public List<Menu> findMenuByRoleId(String rid) {
        return menuMapper.findMenuByRoleId(rid);
    }
}
