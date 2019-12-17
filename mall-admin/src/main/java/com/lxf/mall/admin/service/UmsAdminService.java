package com.lxf.mall.admin.service;

import com.lxf.mall.admin.dto.UmsAdminParam;
import com.lxf.mall.mbg.bo.UmsAdmin;
import com.lxf.mall.mbg.bo.UmsPermission;

import java.util.List;

/**
 * @author xufeng.liu
 * @email xueshzd@163.com
 * @date 2019/12/10 16:49
 */
public interface UmsAdminService {

    UmsAdmin getAdminByUsername(String username);

    List<UmsPermission> getPermissionList(Long id);

    UmsAdmin register(UmsAdminParam umsAdminParam);
}
