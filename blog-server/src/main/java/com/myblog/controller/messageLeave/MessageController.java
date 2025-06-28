package com.myblog.controller.messageLeave;

import com.myblog.dto.MessageDTO;
import com.myblog.result.Result;
import com.myblog.service.MessageService;
import com.myblog.utils.GetImgsUtil;
import com.myblog.vo.MessageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/message")
@Api(tags = "首页相关接口")
@Slf4j
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private GetImgsUtil getImgsUtil;
    /**\
     * 获取留言信息
     * @return
     */
    @GetMapping("/getMessage")
    @ApiOperation("获取留言信息")
    public Result<ArrayList<MessageVO>> getMessage() {
        return Result.success(messageService.getMessage());
    }

    @GetMapping("/getLastId")
    @ApiOperation("获取最后一条信息的id")
    public Result<Long> getLastMessageId(){
        return Result.success(messageService.getLastMessageId());
    }

    @GetMapping("/getFaceImgsNames")
    @ApiOperation("获取图片名字")
    public Result<String[]> getFaceImgsNames(){
        return Result.success(getImgsUtil.getImgsNamesHandler());
    }

    @PostMapping("insertMessage")
    @ApiOperation("添加留言信息")
    public Result<String> insertMessage(@RequestBody MessageDTO messageDTO){
        messageService.insertMessage(messageDTO);
        return Result.success();
    }

}
