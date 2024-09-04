package com.example.lastdemo.cryptography;

import java.security.MessageDigest;

/**
 * HashFunctionExample 클래스는 SHA-256 알고리즘을 사용하여
 * 메시지의 해시 값을 생성하는 기능을 제공합니다.
 */
public class HashFunctionExample {

    /**
     * 주어진 메시지에 대한 SHA-256 해시 값을 생성합니다.
     * 
     * @param message 해시 값을 생성할 메시지
     * @return 생성된 해시 값을 16진수로 표현한 문자열
     * @throws Exception 해시 생성 과정에서 발생할 수 있는 예외
     */
    public String generateHash(String message) throws Exception {
        // SHA-256 해시 알고리즘을 사용하여 해시 값을 생성
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(message.getBytes("UTF-8"));

        // 해시 값을 16진수 문자열로 변환
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
