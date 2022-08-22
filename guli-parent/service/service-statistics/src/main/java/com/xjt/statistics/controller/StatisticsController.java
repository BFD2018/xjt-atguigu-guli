package com.xjt.statistics.controller;


import com.xjt.commonutils.R;
import com.xjt.statistics.service.StatistisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author javaXiong
 * @since 2022-08-22
 */
@RestController
@RequestMapping("/statistics/service")
@CrossOrigin
public class StatisticsController {
    @Autowired
    private StatistisService statistisService;

    //统计某一天注册人数,生成统计数据，写入统计表 statistics_daily
    @PostMapping("registerCount/{day}")
    public R registerCount(@PathVariable("day") String day) {
        statistisService.registerCount(day);
        return R.ok();
    }

    //图表显示，返回两部分数据，日期json数组，数量json数组
    @GetMapping("showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,@PathVariable String begin,
                      @PathVariable String end) {
        Map<String,Object> map = statistisService.getShowData(type,begin,end);
        return R.ok().data(map);
    }
}

