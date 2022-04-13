package com.guli.service_order.service;

import com.guli.service_order.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-04-13
 */
public interface TOrderService extends IService<TOrder> {

    String saveOrder(String courseId, String memberIdByJwtToken);
}
