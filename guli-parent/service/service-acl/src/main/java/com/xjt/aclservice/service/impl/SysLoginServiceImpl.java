package com.xjt.aclservice.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xjt.aclservice.entity.vo.LoginUser;
import com.xjt.aclservice.service.ISysUserService;
import com.xjt.aclservice.service.SysLoginService;
import com.xjt.aclservice.service.TokenService;
import com.xjt.commonutils.ServletUtils;
import com.xjt.commonutils.StringUtils;
import com.xjt.commonutils.constant.CacheConstants;
import com.xjt.commonutils.core.redis.RedisCache;
import com.xjt.commonutils.exception.user.CaptchaException;
import com.xjt.commonutils.exception.user.CaptchaExpireException;
import com.xjt.commonutils.utils.ip.IpUtils;
import com.xjt.security.context.AuthenticationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysLoginServiceImpl implements SysLoginService {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private ISysUserService userService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    @Override
    public String login(String username, String password, String code, String uuid) {
        // 校验验证码
        validateCaptcha(username, code, uuid);

        // 用户验证
        Authentication authentication = null;
        try
        {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            AuthenticationContextHolder.clearContext();
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //将登陆信息写入数据库
        recordLoginInfo(loginUser.getUserId());

        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId)
    {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(userId);
        loginUser.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        loginUser.setLoginTime(DateUtil.current());
        userService.updateUserProfile(loginUser);
    }
}
