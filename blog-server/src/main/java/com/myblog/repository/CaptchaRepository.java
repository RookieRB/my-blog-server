package com.myblog.repository;

import com.myblog.entity.Captcha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface CaptchaRepository extends JpaRepository<Captcha, Long> {
    Captcha findByEmailAndCodeAndExpireTimeAfter(String email, String code, LocalDateTime now);
    void deleteByEmail(String email);
}