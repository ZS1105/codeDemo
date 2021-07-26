package com.atguigu.orderservice.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.orderservice.entity.Order;
import com.atguigu.orderservice.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-07-22
 */
@RestController
@RequestMapping("/orderservice/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    //根据课程id和用户id创建订单，返回订单id
    @GetMapping("/createOrder/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        // System.out.println(memberId);
        String orderId = orderService.createOrder(courseId, memberId);
        // System.out.println(orderId);
        return R.ok().data("orderNo", orderId);
    }

    @GetMapping("getOrder/{orderNo}")
    public R getOrderInfo(@PathVariable String orderNo) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);
        System.out.println("order:" + order);
        return R.ok().data("orderInfo", order);
    }

    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId,
                               @PathVariable String memberId) {

        //订单状态是1表示支付成功
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id",memberId);
        wrapper.eq("course_id",courseId);
        wrapper.eq("status",1);
        //订单状态是1表示支付成功
        int count = orderService.count(wrapper);
        if(count>0) {
            return true;
        } else {
            return false;
        }
    }
}

