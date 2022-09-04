package com.xjt.aclservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjt.aclservice.entity.vo.LoginUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<LoginUser> {
}
