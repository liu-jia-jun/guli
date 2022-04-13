package com.guli.service_order.service.impl;

import com.commonutils.OrderNoUtil;
import com.commonutils.bean.CourseWebVoOrder;
import com.commonutils.bean.UcenterMemberOrder;
import com.guli.service_order.client.EduClient;
import com.guli.service_order.client.UcenterClient;
import com.guli.service_order.entity.TOrder;
import com.guli.service_order.mapper.TOrderMapper;
import com.guli.service_order.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-04-13
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    //创建订单
    @Override
    public String saveOrder(String courseId, String memberId) {
        //远程调用课程服务，根据课程id获取课程信息
        CourseWebVoOrder courseDto = eduClient.getCourseInfoDto(courseId);

        //远程调用用户服务，根据用户id获取用户信息
        UcenterMemberOrder ucenterMember = ucenterClient.getInfo(memberId);

        //创建订单
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseDto.getTitle());
        order.setCourseCover(courseDto.getCover());
        order.setTeacherName(courseDto.getTeacherName());

        order.setTotalFee(courseDto.getPrice());
        order.setMemberId(memberId);
        order.setMobile(ucenterMember.getMobile());
        order.setNickname(ucenterMember.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
