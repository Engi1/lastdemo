package com.example.lastdemo.cryptography;

import java.security.MessageDigest;

public class HashFunctionExample {

    public static void main(String[] args) throws Exception {
        // 원본 메시지
        String originalMessage = "Hello, this is a hashed message!";

        // SHA-256 해시 계산
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(originalMessage.getBytes("UTF-8"));

        // 해시를 16진수 문자열로 변환
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        System.out.println("Hashed Message (SHA-256): " + hexString.toString());
    }
}
