package com.baomidou.samples.localtx.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.samples.localtx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where id=#{id}")
    User Sel(int id);

    @DS("db2")
    @Select("select * from user where id=#{id}")
    User SelDb2(int id);
}