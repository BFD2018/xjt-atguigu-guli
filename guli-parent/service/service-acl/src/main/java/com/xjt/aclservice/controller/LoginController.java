package com.xjt.aclservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.xjt.aclservice.entity.vo.LoginBody;
import com.xjt.aclservice.entity.vo.LoginUser;
import com.xjt.aclservice.service.IndexService;
import com.xjt.aclservice.service.SysLoginService;
import com.xjt.commonutils.R;
import com.xjt.commonutils.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/admin/acl/index")
@CrossOrigin
public class LoginController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private SysLoginService loginService;

    /**
     * 登录方法
     */
    @PostMapping("/login")
    public R login(@RequestBody LoginBody loginBody)
    {
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());

        return R.ok().data(Constants.TOKEN, token);
    }

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public R info(){
        //获取当前登录用户用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String)authentication.getPrincipal();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return R.ok().data(userInfo);
    }


    /**
     * 获取菜单
     * @return
     */
    @GetMapping("menu")
    public R getMenu(){
        //获取当前登录用户用户名
        String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return R.ok().data("permissionList", permissionList);
    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }

}
