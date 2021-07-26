package com.atguigu.orderservice.service.impl;


import com.atguigu.commonutils.vo.CourseInfo;
import com.atguigu.commonutils.vo.TeacherVo;
import com.atguigu.commonutils.vo.UserInfo;
import com.atguigu.orderservice.client.EduClient;
import com.atguigu.orderservice.client.UcenterClient;
import com.atguigu.orderservice.entity.Order;
import com.atguigu.orderservice.mapper.OrderMapper;
import com.atguigu.orderservice.service.OrderService;
import com.atguigu.orderservice.utils.OrderNoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-07-22
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrder(String courseId, String memberId) {
        //远程调用课程服务，根据课程id获取课程信息
        CourseInfo courseInfo = eduClient.getCourseInfoOrder(courseId);
        //远程调用用户服务，根据用户id获取用户信息
        UserInfo memberInfo = ucenterClient.getMemberInfo(memberId);

        String teacherId = courseInfo.getTeacherId();
        System.out.println("教师id：" + teacherId);

        //远程调用用户服务，根据用户id获取教师信息
        TeacherVo teacherInfo = eduClient.getTeacherInfoByTeacherId(teacherId);
        System.out.println("教师名：" + teacherInfo.getName());

        //创建订单
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo()); // 订单号
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfo.getTitle());
        order.setCourseCover(courseInfo.getCover());
        order.setTeacherName(teacherInfo.getName());
        order.setTotalFee(courseInfo.getPrice());
        order.setMemberId(memberId);
        order.setMobile(memberInfo.getMobile());
        order.setNickname(memberInfo.getNickname());

        // System.out.println(order);
        baseMapper.insert(order);

        return order.getOrderNo();

    }
}
