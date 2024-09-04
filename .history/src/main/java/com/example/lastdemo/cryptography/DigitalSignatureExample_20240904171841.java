package com.example.lastdemo.cryptography;

import java.security.*;
import java.util.Base64;

/**
 * DigitalSignatureExample 클래스는 RSA 알고리즘을 사용하여
 * 디지털 서명을 생성하고 검증하는 기능을 제공합니다.
 */
public class DigitalSignatureExample {

    // RSA 키 쌍을 저장하는 정적 필드
    private static KeyPair keyPair;

    // 클래스 로드 시 키 쌍을 생성하고 초기화하는 정적 블록
    static {
        try {
            // RSA 알고리즘을 사용하여 키 쌍 생성
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            keyPair = keyPairGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 주어진 메시지에 대한 디지털 서명을 생성합니다.
     * 
     * @param message 서명할 메시지
     * @return 생성된 디지털 서명을 Base64로 인코딩한 문자열
     * @throws Exception 서명 생성 과정에서 발생할 수 있는 예외
     */
    public String signMessage(String message) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(keyPair.getPrivate());
        signature.update(message.getBytes());
        byte[] digitalSignature = signature.sign();
        return Base64.getEncoder().encodeToString(digitalSignature);
    }

    /**
     * 주어진 메시지와 디지털 서명을 사용하여 서명이 유효한지 검증합니다.
     * 
     * @param message 원본 메시지
     * @param signatureStr 검증할 디지털 서명 (Base64 인코딩된 문자열)
     * @return 서명이 유효하면 true, 그렇지 않으면 false
     * @throws Exception 서명 검증 과정에서 발생할 수 있는 예외
     */
    public boolean verifySignature(String message, String signatureStr) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(keyPair.getPublic());
        signature.update(message.getBytes());
        byte[] digitalSignature = Base64.getDecoder().decode(signatureStr);
        return signature.verify(digitalSignature);
    }
}
