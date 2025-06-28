package com.myblog.service.impl;

import com.myblog.constant.MessageConstant;
import com.myblog.dto.MessageDTO;
import com.myblog.entity.Message;
import com.myblog.entity.User;
import com.myblog.exception.InsertNewMessageFailed;
import com.myblog.mapper.MessageMapper;
import com.myblog.mapper.UserMapper;
import com.myblog.service.MessageService;
import com.myblog.vo.MessageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
            User currentUser = userMapper.getuserBuyId(m.getMessageUser());
            currentMessageVO.setImgUrl(currentUser.getImgUrl());
            currentMessageVO.setLevel(currentUser.getLevel());
            currentMessageVO.setUserNickname(currentUser.getUserNickname());
            ArrayList<MessageVO> childMessageVO = getAllMessageLeave(m.getMessageId());
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

    @Override
    public Long getLastMessageId() {
        Message message = messageMapper.getLastMessage();
        return message.getMessageId();
    }

    public void insertMessage(MessageDTO messageDTO){
        messageDTO.setCreateTime(LocalDateTime.now());
        int i = messageMapper.insertMessage(messageDTO);
        if(i!= 1){
            throw new InsertNewMessageFailed(MessageConstant.INSERT_FAILED);
        }
    }


    @Override
    public void insertMultiMessage(ArrayList<MessageDTO> messageDTO) {
        for(MessageDTO currentDTO : messageDTO){
            currentDTO.setCreateTime(LocalDateTime.now());
           int i = messageMapper.insertMessage(currentDTO);
           if(i!= 1){
               throw new InsertNewMessageFailed(MessageConstant.INSERT_FAILED);
           }
        }
    }
}
