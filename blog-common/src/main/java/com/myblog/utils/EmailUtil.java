package com.myblog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender mailSender;

    public void sendCaptchaEmail(String to, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("your-email@example.com");
            helper.setTo(to);
            helper.setSubject("验证码");

            String content = ""
                    + "<div style=\"font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #eee; border-radius: 5px;\">\n"
                    + "  <h2 style=\"color: #333;\">您的验证码</h2>\n"
                    + "  <p style=\"font-size: 16px; color: #666;\">您好，您的验证码是：</p>\n"
                    + "  <div style=\"background-color: #f5f5f5; padding: 10px; text-align: center; font-size: 24px; font-weight: bold; letter-spacing: 5px; margin: 20px 0;\">\n"
                    + "    " + code + "\n"
                    + "  </div>\n"
                    + "  <p style=\"font-size: 14px; color: #999;\">验证码有效期为10分钟，请勿泄露给他人。</p>\n"
                    + "</div>";

            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("发送邮件失败", e);
        }
    }
}