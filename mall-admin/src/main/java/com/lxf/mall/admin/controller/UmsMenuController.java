package com.lxf.mall.admin.controller;

import com.lxf.mall.admin.service.UmsMenuService;
import com.lxf.mall.common.api.CommonResult;
import com.lxf.mall.mbg.bo.UmsMenu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台菜单管理
 * @author xufeng.liu
 * @email xueshzd@163.com
 * @date 2020/3/18 10:48
 */
@Controller
@Api(tags = "UmsMenuController",description = "后台菜单管理")
@RequestMapping("/menu")
public class UmsMenuController {

    @Autowired
    private UmsMenuService umsMenuService;


    @ApiOperation("添加后台菜单")
    @RequestMapping(value = "create",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody UmsMenu umsMenu){
        int count = umsMenuService.create(umsMenu);
        if(count > 0 ){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

}
