package com.atguigu.orderservice.service;

import com.atguigu.orderservice.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author zs
 * @since 2021-07-22
 */
public interface OrderService extends IService<Order> {

    String createOrder(String courseId, String memberId);
}
