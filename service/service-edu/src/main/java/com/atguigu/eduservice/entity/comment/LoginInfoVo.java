package com.atguigu.eduservice.entity.comment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author: shuai
 * Date: 2021/7/22
 * desc:
 */
@Data
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
