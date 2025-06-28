package com.myblog.service.impl;

import com.myblog.constant.MessageConstant;
import com.myblog.entity.Captcha;
import com.myblog.exception.SendEmailCodeFailedException;
import com.myblog.repository.CaptchaRepository;
import com.myblog.result.Result;
import com.myblog.service.CaptchaService;

import com.myblog.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Random;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private CaptchaRepository captchaRepository;

    @Autowired
    private MailUtils emailUtil;

    @Override
    @Transactional
    public Result sendCaptcha(String email) {
        // 生成6位随机验证码
        String code = generateCaptcha();

        // 设置过期时间为10分钟后
        LocalDateTime expireTime = LocalDateTime.now().plusMinutes(10);

        // 删除该邮箱的旧验证码
        captchaRepository.deleteByEmail(email);

        // 保存新验证码
        Captcha captcha = new Captcha();
        captcha.setEmail(email);
        captcha.setCode(code);
        captcha.setExpireTime(expireTime);
        captcha.setCreateTime(LocalDateTime.now());
        captchaRepository.save(captcha);

        // 发送验证码邮件
        try {
            emailUtil.snedSimpleMail(email,"邮箱验证码", code);
        } catch (Exception e) {
            throw new SendEmailCodeFailedException(MessageConstant.SEND_EMAIL_CODE_FAILED);
        }

        return Result.success("验证码已发送");
    }

    @Override
    public boolean verifyCaptcha(String email, String code) {
        Captcha captcha = captchaRepository.findByEmailAndCodeAndExpireTimeAfter(email, code, LocalDateTime.now());
        return captcha != null;
    }

    private String generateCaptcha() {
        Random random = new Random();
        int captcha = 100000 + random.nextInt(900000);
        return String.valueOf(captcha);
    }
}