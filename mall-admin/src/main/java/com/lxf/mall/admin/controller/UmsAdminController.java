package com.lxf.mall.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.lxf.mall.admin.dao.UmsAdminRoleRelationDao;
import com.lxf.mall.admin.dto.UmsAdminLoginParam;
import com.lxf.mall.admin.dto.UmsAdminParam;
import com.lxf.mall.admin.service.UmsAdminService;
import com.lxf.mall.common.api.CommonPage;
import com.lxf.mall.common.api.CommonResult;
import com.lxf.mall.mbg.bo.UmsAdmin;
import com.lxf.mall.mbg.bo.UmsPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户管理
 * @author xufeng.liu
 * @email xueshzd@163.com
 * @date 2019/12/11 14:45
 */
@Controller
@RequestMapping("/admin")
@Api(tags = "UmsAdminController",description = "后台用户管理")
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ResponseBody
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    @ApiOperation("用户注册")
    public CommonResult register(@RequestBody UmsAdminParam umsAdminParam){
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if(umsAdmin == null){
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ResponseBody
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ApiOperation("获取当前登录用户信息")
    public CommonResult info(Principal principal){
        String name = principal.getName();
        UmsAdmin umsAdmin = adminService.getAdminByUsername(name);
        Map<String,Object> map = new HashMap<>();
        map.put("username",umsAdmin.getUsername());
        map.put("roles",new String[]{"TEST"});
        map.put("icon",umsAdmin.getIcon());
        return CommonResult.success(map);

    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation("用户登录，返回token")
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result){
        String token = adminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
        if(StrUtil.isBlank(token)){
            CommonResult.validateFailed("用户名或者密码为空");
        }
        Map<String,Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("刷新token")
    @RequestMapping(value = "/refreshToken",method = RequestMethod.GET)
    public CommonResult refreshToken(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if(refreshToken == null){
            return CommonResult.failed("token已经过期");
        }
        Map<String,Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ResponseBody
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ApiOperation("用户退出")
    public CommonResult logout(){
        adminService.logout();
        return CommonResult.success(null);
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsAdmin> adminList = adminService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId){
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }

}
