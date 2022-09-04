package com.xjt.aclservice.service;

import com.xjt.aclservice.entity.vo.LoginUser;

public interface ISysUserService {
    void updateUserProfile(LoginUser loginUser);

    LoginUser selectUserByUserName(String username);
}
