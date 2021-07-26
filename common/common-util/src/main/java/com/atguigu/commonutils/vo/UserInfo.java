package com.atguigu.commonutils.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author: shuai
 * Date: 2021/7/22
 * desc:
 */
@Data
@ApiModel(value = "用户基本信息", description = "编辑用户基本信息的表单对象")
public class UserInfo {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "性别")
    private Integer sex;
}
