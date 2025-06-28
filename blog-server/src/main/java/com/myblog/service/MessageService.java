package com.myblog.service;

import com.myblog.dto.MessageDTO;
import com.myblog.vo.MessageVO;

import java.util.ArrayList;

public interface MessageService {
    ArrayList<MessageVO> getMessage();
    Long getLastMessageId();
    void insertMessage(MessageDTO messageDTO);
    void insertMultiMessage(ArrayList<MessageDTO> messageDTO);
}
