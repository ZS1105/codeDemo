package com.atguigu.msmservice.service;

import java.util.Map;

/**
 * Author: shuai
 * Date: 2021/7/19
 * desc:
 */
public interface MsmService {
    boolean send(String phone, Map<String, Object> param);
}
