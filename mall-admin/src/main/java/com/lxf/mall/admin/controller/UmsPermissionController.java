package com.lxf.mall.admin.controller;

import com.lxf.mall.admin.service.UmsAdminService;
import com.lxf.mall.common.api.CommonResult;
import com.lxf.mall.mbg.bo.UmsPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author xufeng.liu
 * @email xueshzd@163.com
 * @date 2019/12/11 16:25
 * 权限管理
 */
@Controller
@Api(tags = "UmsPermissionController", description = "后台用户权限管理")
@RequestMapping("/permission")
public class UmsPermissionController {

    @Autowired
    private UmsAdminService umsAdminService;

    @ApiOperation("获取所有权限列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPermission>> list(){
        return null;
    }

}
