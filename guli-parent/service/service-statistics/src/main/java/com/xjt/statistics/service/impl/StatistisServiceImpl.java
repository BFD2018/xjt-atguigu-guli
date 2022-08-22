package com.xjt.statistics.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjt.commonutils.R;
import com.xjt.statistics.client.UcenterClient;
import com.xjt.statistics.entity.Statistics;
import com.xjt.statistics.mapper.StatisticsMapper;
import com.xjt.statistics.service.StatistisService;
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
 * @author javaXiong
 * @since 2022-08-22
 */
@Service
public class StatistisServiceImpl extends ServiceImpl<StatisticsMapper, Statistics> implements StatistisService {
    @Autowired
    private UcenterClient ucenterClient;

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public void registerCount(String day) {
        //添加记录之前删除表相同日期的数据
        QueryWrapper<Statistics> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        statisticsMapper.delete(wrapper);

        //远程调用得到某一天注册人数
        R r = ucenterClient.countRegister(day);
        Integer countRegister = (Integer) r.getData().get("countRegister");

        //把获取数据添加数据库，统计分析表里面
        Statistics sta = new Statistics();
        sta.setDateCalculated(day);
        sta.setRegisterNum(countRegister);      //设置某一天的注册人数
        //下面的几项 用随机数模拟
        sta.setVideoViewNum(RandomUtil.randomInt(1000));
        sta.setLoginNum(RandomUtil.randomInt(100));
        sta.setCourseNum(50);

        statisticsMapper.insert(sta);
    }

    //图表显示，返回两部分数据，日期json数组，数量json数组
    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        //根据条件查询对应数据
        QueryWrapper<Statistics> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        switch (type) {
            case "login_num":
                wrapper.select("login_num", type);
                break;
            case "register_num":
                wrapper.select("register_num", type);
                break;
            case "video_view_num":
                wrapper.select("video_view_num", type);
                break;
            case "course_num":
                wrapper.select("course_num", type);
                break;
            default:
                break;
        }
        List<Statistics> staList = baseMapper.selectList(wrapper);
        //因为返回有两部分数据：日期 和 日期对应数量
        //前端要求数组json结构，对应后端java代码是list集合
        //创建两个list集合，一个日期list，一个数量list
        List<String> date_calculatedList = new ArrayList<>();
        List<Integer> numDataList = new ArrayList<>();
        //遍历查询所有数据list集合，进行封装
        for (int i = 0; i < staList.size(); i++) {
            Statistics daily = staList.get(i);
            //封装日期list集合
            date_calculatedList.add(daily.getDateCalculated());
            //封装对应数量
            switch (type) {
                case "login_num":
                    numDataList.add(daily.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    numDataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        //把封装之后两个list集合放到map集合，进行返回
        Map<String, Object> map = new HashMap<>();
        map.put("date_calculatedList",date_calculatedList);
        map.put("numDataList",numDataList);
        return map;
    }
}
