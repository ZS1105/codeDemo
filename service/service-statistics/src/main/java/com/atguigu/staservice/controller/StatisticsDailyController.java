package com.atguigu.staservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-07-23
 */
@RestController
@RequestMapping("/staservice/statisticsDaily")
// @CrossOrigin
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService staservice;

    @GetMapping("/createStatisticsByDay/{day}")
    public R createStatisticsByDate(@PathVariable String day) {
        staservice.createStatisticsByDay(day);
        return R.ok();
    }

    @GetMapping("showChart/{begin}/{end}/{type}")
    public R showChart(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
            Map<String, Object> map = staservice.getChartData(begin, end, type);
        return R.ok().data(map);
    }
}

