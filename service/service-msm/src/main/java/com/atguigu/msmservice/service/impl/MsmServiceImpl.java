package com.atguigu.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.commonutils.ConstantUtil;
import com.atguigu.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Author: shuai
 * Date: 2021/7/19
 * desc:
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(String phone,  Map<String, Object> param) {
        if (StringUtils.isEmpty(phone)){
            return false;
        }



        DefaultProfile profile = DefaultProfile.getProfile("default", ConstantUtil.ACCESS_KEY_ID, ConstantUtil.ACCESS_KEY_SECRET);
        DefaultAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", ConstantUtil.SIGN_NAME);
        request.putQueryParameter("TemplateCode", ConstantUtil.TEMPLATE_CODE);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
/**
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());

            return response.getHttpResponse().isSuccess();

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
 **/

        // return false;

        System.out.println("电话：" + phone + ",验证码：" + param.getOrDefault("code",""));
        return true;
    }
}
