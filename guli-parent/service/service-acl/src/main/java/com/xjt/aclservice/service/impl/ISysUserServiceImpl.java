package com.xjt.aclservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjt.aclservice.entity.vo.LoginUser;
import com.xjt.aclservice.mapper.SysUserMapper;
import com.xjt.aclservice.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ISysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public void updateUserProfile(LoginUser loginUser) {
        userMapper.update(loginUser,null);
    }

    @Override
    public LoginUser selectUserByUserName(String username) {
        QueryWrapper<LoginUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user.username",username);
        LoginUser selectOne = userMapper.selectOne(wrapper);
        return selectOne;
    }
}
