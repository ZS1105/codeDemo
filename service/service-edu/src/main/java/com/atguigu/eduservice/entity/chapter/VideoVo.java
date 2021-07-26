package com.atguigu.eduservice.entity.chapter;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Author: shuai
 * Date: 2021/7/14
 * desc:
 */
@ApiModel(value = "小节信息")
@Data
public class VideoVo {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Boolean isFree;
    private String videoSourceId;
}
