package com.xjt.aclservice.service;

public interface SysLoginService {
    String login(String username, String password, String code, String uuid);
}
