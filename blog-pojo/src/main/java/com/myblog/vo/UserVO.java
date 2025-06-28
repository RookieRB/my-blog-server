package com.myblog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询用户是否处于登录状态，返回用户信息")
public class UserVO {
    @ApiModelProperty("主键值")
    private Long id;
    @ApiModelProperty("用户图片")
    private String imgUrl;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户等级")
    private Integer level;
    @ApiModelProperty("用户名昵称")
    private String userNickname;
}
