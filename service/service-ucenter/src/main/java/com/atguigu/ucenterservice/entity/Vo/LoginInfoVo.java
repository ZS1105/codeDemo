package com.atguigu.ucenterservice.entity.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: shuai
 * Date: 2021/7/19
 * desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfoVo {
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
