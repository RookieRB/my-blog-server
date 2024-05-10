package com.myblog.mapper;

import com.myblog.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface MessageMapper {
    @Select("select * from message where parent_id = #{id}")
    ArrayList<Message> getMessageByParentId(Long id);
}
