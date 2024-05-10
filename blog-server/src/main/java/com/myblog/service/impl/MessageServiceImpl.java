package com.myblog.service.impl;

import com.myblog.entity.Message;
import com.myblog.entity.User;
import com.myblog.mapper.MessageMapper;
import com.myblog.mapper.UserMapper;
import com.myblog.service.MessageService;
import com.myblog.vo.MessageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;
    /**
     * 找寻子留言
     * @param message
     * @return
     */
    private ArrayList<MessageVO> getAllMessageLeave(Long parentId){
        ArrayList<Message> messageArray = messageMapper.getMessageByParentId(parentId);
        if(messageArray == null || messageArray.isEmpty() ){
            return null;
        }
        ArrayList<MessageVO> messageVOList = new ArrayList<>();
        for(Message m : messageArray){
            MessageVO currentMessageVO = new MessageVO();
            BeanUtils.copyProperties(m,currentMessageVO);
            User currentUser = userMapper.getUserById(m.getMessageUser());
            currentMessageVO.setUserImg(currentUser.getImgUrl());
            currentMessageVO.setUserLevel(currentUser.getLevel());
            currentMessageVO.setUserName(currentUser.getUserNickname());
            ArrayList<MessageVO> childMessageVO = getAllMessageLeave(m.getMessageID());
            currentMessageVO.setChildMessages(childMessageVO);
            messageVOList.add(currentMessageVO);
        }
        return messageVOList;
    }

    // 获取所有留言信息
    @Override
    public ArrayList<MessageVO> getMessage() {
        return getAllMessageLeave(0L);
    }
}
