package com.myblog.service;


import com.myblog.result.Result;

public interface QrCodeService {
    Result generateQrCode();
    Result checkQrCodeStatus(String qrCodeId);
    Result confirmQrCodeLogin(String qrCodeId, Long userId);
}