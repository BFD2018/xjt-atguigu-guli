package com.xjt.ucenter.service;

import com.xjt.ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjt.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author javaXiong
 * @since 2022-08-19
 */
public interface MemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    boolean register(RegisterVo registerVo);

    Integer countRegisterDay(String day);
}
