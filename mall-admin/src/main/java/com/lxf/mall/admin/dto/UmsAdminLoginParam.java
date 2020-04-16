package com.lxf.mall.admin.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 后台用户登录参数验证
 * @author xufeng.liu
 * @email xueshzd@163.com
 * @date 2020/3/11 15:03
 */
@Getter
@Setter
public class UmsAdminLoginParam {

    @ApiModelProperty(value = "用户名",required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

}
