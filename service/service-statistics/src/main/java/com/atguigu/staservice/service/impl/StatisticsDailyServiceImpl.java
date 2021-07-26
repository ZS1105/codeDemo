package com.atguigu.staservice.service.impl;

import com.atguigu.staservice.client.EduClient;
import com.atguigu.staservice.client.UcenterClient;
import com.atguigu.staservice.entity.StatisticsDaily;
import com.atguigu.staservice.mapper.StatisticsDailyMapper;
import com.atguigu.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-07-23
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private UcenterClient ucenterClient;
    @Autowired
    private EduClient eduClient;

    @Override
    public void createStatisticsByDay(String day) {
        //删除已存在的统计对象
        QueryWrapper<StatisticsDaily> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.eq("date_calculated", day);
        baseMapper.delete(dayQueryWrapper);

        //获取统计信息
        Integer registerNum = (Integer) ucenterClient.staticRegisterNumByDay(day).getData().get("count");
        Integer loginNum = (Integer) ucenterClient.staticLoginrNum(day).getData().get("count");//TODO
        Integer videoViewNum = (Integer) eduClient.staticViewNum(day).getData().get("count");//TODO
        Integer courseNum = (Integer) eduClient.staticCourseNum(day).getData().get("count");//TODO

        //创建统计对象
        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(registerNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);
        baseMapper.insert(daily);
    }

    @Override
    public Map<String, Object> getChartData(String begin, String end, String type) {
        QueryWrapper<StatisticsDaily> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.select(type, "date_calculated");
        dayQueryWrapper.between("date_calculated", begin, end);
        List<StatisticsDaily> dayList = baseMapper.selectList(dayQueryWrapper);

        Map<String, Object> map = new HashMap<>();
        List<Integer> ydataList = new ArrayList<Integer>();
        List<String> xdateList = new ArrayList<String>();
        map.put("x", xdateList);
        map.put("y", ydataList);

        for (int i = 0; i < dayList.size(); i++) {
            StatisticsDaily daily = dayList.get(i);
            xdateList.add(daily.getDateCalculated());
            switch (type) {
                case "register_num":
                    ydataList.add(daily.getRegisterNum());
                    break;
                case "login_num":
                    ydataList.add(daily.getLoginNum());
                    break;
                case "video_view_num":
                    ydataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    ydataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        return map;
    }
}
