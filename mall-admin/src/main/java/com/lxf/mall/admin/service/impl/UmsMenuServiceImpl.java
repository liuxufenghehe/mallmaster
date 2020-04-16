package com.lxf.mall.admin.service.impl;

import com.lxf.mall.admin.service.UmsMenuService;
import com.lxf.mall.mbg.bo.UmsMenu;
import com.lxf.mall.mbg.mapper.UmsMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 后台菜单管理service
 * @author xufeng.liu
 * @email xueshzd@163.com
 * @date 2020/3/18 11:20
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService{

    @Autowired
    private UmsMenuMapper umsMenuMapper;


    @Override
    public int create(UmsMenu umsMenu) {
        umsMenu.setCreateTime(new Date());
        updateLevel(umsMenu);
        int count = umsMenuMapper.insert(umsMenu);
        return count;
    }

    private void updateLevel(UmsMenu umsMenu) {
        if(umsMenu.getParentId() == 0){
            //没有父级为一级菜单
            umsMenu.setLevel(0);
        } else {
            UmsMenu p_umsMenu = umsMenuMapper.selectByPrimaryKey(umsMenu.getParentId());
            if(p_umsMenu != null){
                umsMenu.setLevel(p_umsMenu.getLevel() + 1);
            }else{
                umsMenu.setLevel(0);
            }
        }
    }

}
