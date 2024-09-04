package com.example.lastdemo.cryptography;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DigitalSignatureExample {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public DigitalSignatureExample(String publicKeyStr, String privateKeyStr) throws Exception {
        // Base64로 인코딩된 공개키와 개인키를 디코딩합니다.
        byte[] publicBytes = Base64.getDecoder().decode(publicKeyStr);
        byte[] privateBytes = Base64.getDecoder().decode(privateKeyStr);

        // 공개키와 개인키의 스펙을 정의합니다.
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(publicBytes);
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(privateBytes);

        // RSA 알고리즘을 사용하는 KeyFactory 인스턴스를 생성합니다.
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.publicKey = keyFactory.generatePublic(pubKeySpec);
        this.privateKey = keyFactory.generatePrivate(privKeySpec);
    }

    // 메시지를 서명하는 메서드
    public String signMessage(String message) throws Exception {
        // SHA256withRSA 알고리즘을 사용하여 서명 객체를 생성합니다.
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);  // 개인키로 서명을 초기화합니다.
        signature.update(message.getBytes("UTF-8"));  // 서명할 메시지를 업데이트합니다.

        // 서명을 생성하고 Base64로 인코딩하여 반환합니다.
        byte[] digitalSignature = signature.sign();
        return Base64.getEncoder().encodeToString(digitalSignature);
    }

    // 서명을 검증하는 메서드
    public boolean verifySignature(String message, String signatureStr) throws Exception {
        // SHA256withRSA 알고리즘을 사용하여 서명 검증 객체를 생성합니다.
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);  // 공개키로 서명 검증을 초기화합니다.
        signature.update(message.getBytes("UTF-8"));  // 검증할 메시지를 업데이트합니다.

        // Base64로 인코딩된 서명을 디코딩하고 검증 결과를 반환합니다.
        byte[] digitalSignature = Base64.getDecoder().decode(signatureStr);
        return signature.verify(digitalSignature);
    }
}
