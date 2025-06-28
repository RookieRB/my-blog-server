package com.myblog.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "qr_code")
public class QrCode {
    @Id
    private String id;

    private Integer status = 0; // 0-未扫描，1-已扫描未确认，2-已确认，3-已过期

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "expire_time", nullable = false)
    private LocalDateTime expireTime;

    @Column(name = "create_time")
    private LocalDateTime createTime;
}