package com.myblog.mapper;

import com.myblog.dto.MessageDTO;
import com.myblog.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface MessageMapper {
    @Select("select * from message where parent_id = #{id} order by create_time")
    ArrayList<Message> getMessageByParentId(Long id);

    @Select("select * from message  order by message_id desc limit 1")
    Message getLastMessage();

    int insertMessage(MessageDTO currentDTO);
}
