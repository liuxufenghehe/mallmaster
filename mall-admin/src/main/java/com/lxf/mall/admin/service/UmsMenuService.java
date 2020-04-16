package com.lxf.mall.admin.service;

import com.lxf.mall.mbg.bo.UmsMenu; /**
 * @author xufeng.liu
 * @email xueshzd@163.com
 * @date 2020/3/18 11:18
 */
public interface UmsMenuService {

    /**
     * 创建菜单
     * @param umsMenu
     * @return
     */
    int create(UmsMenu umsMenu);
}
