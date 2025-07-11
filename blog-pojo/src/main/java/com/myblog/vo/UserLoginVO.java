package com.myblog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户登录后返回的数据结构")
public class UserLoginVO {
    @ApiModelProperty("主键值")
    private Long id;
    @ApiModelProperty("用户图片")
    private String imgUrl;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("jwt令牌")
    private String token;
    @ApiModelProperty("用户等级")
    private Integer level;
    @ApiModelProperty("用户昵称")
    private String userNickname;
}
