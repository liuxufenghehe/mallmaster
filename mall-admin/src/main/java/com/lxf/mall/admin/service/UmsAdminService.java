package com.lxf.mall.admin.service;

import com.lxf.mall.admin.dto.UmsAdminParam;
import com.lxf.mall.mbg.bo.UmsAdmin;
import com.lxf.mall.mbg.bo.UmsPermission;
import org.springframework.security.core.userdetails.UserDetails;

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

    /**
     * 登录生成token
     * @param username
     * @param password
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 刷新用户token
     * @param token
     * @return
     */
    String refreshToken(String token);

    /**
     * 用户退出
     */
    void logout();

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);
}
