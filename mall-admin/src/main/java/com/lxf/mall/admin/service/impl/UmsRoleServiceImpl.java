package com.lxf.mall.admin.service.impl;

import com.lxf.mall.admin.service.UmsRoleService;
import com.lxf.mall.mbg.bo.UmsMenu;
import com.lxf.mall.mbg.bo.UmsPermission;
import com.lxf.mall.mbg.bo.UmsResource;
import com.lxf.mall.mbg.bo.UmsRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xufeng.liu
 * @email xueshzd@163.com
 * @date 2020/4/17 14:00
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {


    @Override
    public int create(UmsRole role) {
        return 0;
    }

    @Override
    public int update(Long id, UmsRole role) {
        return 0;
    }

    @Override
    public int delete(List<Long> ids) {
        return 0;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long roleId) {
        return null;
    }

    @Override
    public int updatePermission(Long roleId, List<Long> permissionIds) {
        return 0;
    }

    @Override
    public List<UmsRole> list() {
        return null;
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return null;
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return null;
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return null;
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        return 0;
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        return 0;
    }
}
