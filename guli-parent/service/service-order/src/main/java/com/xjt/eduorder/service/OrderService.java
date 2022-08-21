package com.xjt.eduorder.service;

import com.xjt.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author javaXiong
 * @since 2022-08-21
 */
public interface OrderService extends IService<Order> {

    String createOrders(String courseId, String memberId);
}
