package com.myblog.mapper;

import com.myblog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User getUserByName(String username);

    @Select("select * from user where id = #{userId}")
    User getUserById(Long userId);
}
