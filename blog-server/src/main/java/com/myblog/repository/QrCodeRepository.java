package com.myblog.repository;

import com.myblog.entity.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface QrCodeRepository extends JpaRepository<QrCode, String> {
    QrCode findByIdAndExpireTimeAfter(String id, LocalDateTime now);
}