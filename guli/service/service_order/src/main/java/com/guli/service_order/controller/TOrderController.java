package com.guli.service_order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.commonutils.JwtUtils;
import com.commonutils.Result;
import com.guli.service_order.entity.TOrder;
import com.guli.service_order.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-04-13
 */
@RestController
@RequestMapping("/orderservice/order")
@CrossOrigin
public class TOrderController {
    @Autowired
    private TOrderService orderService;
    //根据课程id和用户id创建订单，返回订单id
    @PostMapping("createOrder/{courseId}")
    public Result save(@PathVariable String courseId, HttpServletRequest request) {
        String orderId = orderService.saveOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return Result.ok().data("orderId", orderId);
    }
    @GetMapping("getOrder/{orderId}")
    public Result get(@PathVariable String orderId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        TOrder order = orderService.getOne(wrapper);
        return Result.ok().data("item", order);
    }
}

