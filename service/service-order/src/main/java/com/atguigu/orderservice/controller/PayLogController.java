package com.atguigu.orderservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.orderservice.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-07-22
 */
@RestController
@RequestMapping("/orderservice/paylog")
@CrossOrigin
public class PayLogController {
    @Autowired
    private PayLogService payService;

    @GetMapping("/createPayCode/{orderNo}")
    public R createPayCode(@PathVariable String orderNo) {
        Map map = payService.createPayCode(orderNo);
        System.out.println("createPayCode:" + map);
        return R.ok().data(map);
    }

    @GetMapping("/checkPayStatus/{orderNo}")
    public R checkPayStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String, String> map = payService.checkPayStatus(orderNo);
        System.out.println("checkPayStatus:" + map);

        if (map == null) {//出错
            return R.error().message("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {//如果成功
            //更改订单状态
            payService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }
}

