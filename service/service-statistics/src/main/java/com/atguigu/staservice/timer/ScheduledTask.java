package com.atguigu.staservice.timer;

import com.atguigu.staservice.service.StatisticsDailyService;
import com.atguigu.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Author: shuai
 * Date: 2021/7/23
 * desc:
 */
@Component
public class ScheduledTask {
    @Autowired
    private StatisticsDailyService staservice;

    // 每天一点自动统计前一天的数据
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
    //获取上一天的日期
        String day = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        staservice.createStatisticsByDay(day);
    }
}
