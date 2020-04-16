package com.lxf.mall.admin.dao;

import com.lxf.mall.mbg.bo.UmsPermission;

import java.util.List;

/**
 * @author xufeng.liu
 * @email xueshzd@163.com
 * @date 2019/12/11 11:06
 */
public interface UmsAdminRoleRelationDao {
    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermission> getPermissionList(Long id);

}
