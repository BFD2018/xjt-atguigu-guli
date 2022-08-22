package com.xjt.ucenter.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjt.commonutils.JwtUtils;
import com.xjt.servicebase.exception.GuliException;
import com.xjt.ucenter.entity.UcenterMember;
import com.xjt.ucenter.entity.vo.RegisterVo;
import com.xjt.ucenter.mapper.MemberMapper;
import com.xjt.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author javaXiong
 * @since 2022-08-19
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, UcenterMember> implements MemberService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public String login(UcenterMember member) {
        //获取登录手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();

        //手机号和密码非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001,"登录失败");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if(mobileMember == null) {//没有这个手机号
            throw new GuliException(20001,"登录失败");
        }

        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式 MD5
        if(!SecureUtil.md5(password).equals(mobileMember.getPassword())) {
            throw new GuliException(20001,"登录失败");
        }

        //判断用户是否禁用
        if(mobileMember.getIsDisabled()) {
            throw new GuliException(20001,"登录失败");
        }

        //登录成功
        //生成token字符串，使用jwt工具类
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;
    }

    @Override
    public boolean register(RegisterVo registerVo) {
        //获取注册的数据
        String code = registerVo.getCode(); //验证码
        String mobile = registerVo.getMobile(); //手机号
        String nickname = registerVo.getNickname(); //昵称
        String password = registerVo.getPassword(); //密码

        //非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)) {
            throw new GuliException(20001,"注册失败");
        }
        //判断验证码
        //获取redis验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(redisCode)) {
            throw new GuliException(20001,"验证码输入错误，注册失败");
        }

        //判断手机号是否重复，表里面存在相同手机号不进行添加
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = memberMapper.selectCount(wrapper);
        if(count > 0) {
            throw new GuliException(20001,"该手机号已存在，注册失败");
        }

        //数据添加数据库中
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(SecureUtil.md5(password));//密码需要加密的
        member.setIsDisabled(false);//用户不禁用
        member.setAvatar("https://dd-static.jd.com/ddimg/jfs/t1/194379/10/27495/151587/62ff51a0E660bce0b/9ac417b7cc72fcf6.png");
        int insert = memberMapper.insert(member);

        return insert>0;
    }

    @Override
    public Integer countRegisterDay(String day) {
        return memberMapper.countRegisterDay(day);
    }
}
