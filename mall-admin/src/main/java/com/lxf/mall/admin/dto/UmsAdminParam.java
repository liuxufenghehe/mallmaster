package com.lxf.mall.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author xufeng.liu
 * @email xueshzd@163.com
 * @date 2019/12/11 15:52
 * 用户注册参数
 */
@Getter
@Setter
public class UmsAdminParam {

    @ApiModelProperty(value = "用户名称",required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "头像")
    private String icon;

    @Email(message = "邮箱格式不合法")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "备注信息")
    private String note;

}
