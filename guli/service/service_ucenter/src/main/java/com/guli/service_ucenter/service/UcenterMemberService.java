package com.guli.service_ucenter.service;

import com.guli.service_ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.service_ucenter.entity.vo.LoginInfoVo;
import com.guli.service_ucenter.entity.vo.LoginVo;
import com.guli.service_ucenter.entity.vo.RegisterVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-04-06
 */

public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    UcenterMember getLoginInfo(String memberId);

    UcenterMember getByOpenid(String openid);
}
