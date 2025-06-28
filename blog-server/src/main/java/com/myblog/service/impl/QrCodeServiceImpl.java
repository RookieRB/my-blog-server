package com.myblog.service.impl;

import com.myblog.constant.JwtClaimsConstant;
import com.myblog.entity.QrCode;
import com.myblog.entity.User;
import com.myblog.repository.QrCodeRepository;
import com.myblog.repository.UserRepository;
import com.myblog.result.Result;
import com.myblog.service.QrCodeService;
import com.myblog.properties.JwtProperties;

import com.myblog.utils.JwtUtil;
import com.myblog.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    @Autowired
    private QrCodeRepository qrCodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProperties jwtProperties ;

    @Override
    public Result generateQrCode() {
        // 生成唯一ID
        String qrCodeId = UUID.randomUUID().toString();

        // 设置过期时间为5分钟后
        LocalDateTime expireTime = LocalDateTime.now().plusMinutes(5);

        // 保存二维码信息
        QrCode qrCode = new QrCode();
        qrCode.setId(qrCodeId);
        qrCode.setStatus(0); // 未扫描
        qrCode.setExpireTime(expireTime);
        qrCode.setCreateTime(LocalDateTime.now());
        qrCodeRepository.save(qrCode);

        Map<String, Object> data = new HashMap<>();
        data.put("qrCodeId", qrCodeId);

        return Result.success(data);
    }

    @Override
    public Result checkQrCodeStatus(String qrCodeId) {
        QrCode qrCode = qrCodeRepository.findById(qrCodeId).orElse(null);

        if (qrCode == null) {
            return Result.error("二维码不存在");
        }

        // 检查是否过期
        if (qrCode.getExpireTime().isBefore(LocalDateTime.now())) {
            qrCode.setStatus(3); // 已过期
            qrCodeRepository.save(qrCode);

            Map<String, Object> data = new HashMap<>();
            data.put("status", "expired");
            return Result.success(data);
        }

        Map<String, Object> data = new HashMap<>();

        // 根据状态返回不同信息
        switch (qrCode.getStatus()) {
            case 0:
                data.put("status", "pending");
                break;
            case 1:
                data.put("status", "scanned");
                break;
            case 2:
                data.put("status", "confirmed");
                // 如果已确认，返回用户信息和token
                if (qrCode.getUserId() != null) {
                    User user = userRepository.findById(qrCode.getUserId()).orElse(null);
                    if (user != null) {
                        //登录成功后，生成jwt令牌
                        Map<String, Object> claims = new HashMap<>();
                        claims.put(JwtClaimsConstant.USER_ID, user.getId());
                        String token = JwtUtil.createJWT(
                                jwtProperties.getUserSecretKey(),
                                jwtProperties.getUserTtl(),
                                claims);
                        UserVO userVO = new UserVO();
                        BeanUtils.copyProperties(user, userVO);
                        data.put("token", token);
                        data.put("userInfo", userVO);
                    }
                }
                break;
            case 3:
                data.put("status", "expired");
                break;
        }

        return Result.success(data);
    }

    @Override
    public Result confirmQrCodeLogin(String qrCodeId, Long userId) {
        QrCode qrCode = qrCodeRepository.findByIdAndExpireTimeAfter(qrCodeId, LocalDateTime.now());

        if (qrCode == null) {
            return Result.error("二维码不存在或已过期");
        }

        // 检查用户是否存在
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return Result.error("用户不存在");
        }

        // 更新二维码状态为已确认
        qrCode.setStatus(2); // 已确认
        qrCode.setUserId(userId);
        qrCodeRepository.save(qrCode);

        return Result.success("确认成功");
    }
}