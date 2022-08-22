package com.xjt.ucenter.mapper;

import com.xjt.ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author javaXiong
 * @since 2022-08-19
 */
@Mapper
public interface MemberMapper extends BaseMapper<UcenterMember> {

    Integer countRegisterDay(String day);
}
