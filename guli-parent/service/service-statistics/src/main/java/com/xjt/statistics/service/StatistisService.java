package com.xjt.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjt.statistics.entity.Statistics;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author javaXiong
 * @since 2022-08-22
 */
public interface StatistisService extends IService<Statistics> {

    void registerCount(String day);

    Map<String, Object> getShowData(String type, String begin, String end);
}
