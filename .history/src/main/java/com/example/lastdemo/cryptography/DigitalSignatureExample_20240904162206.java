package com.example.lastdemo.cryptography;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DigitalSignatureExample {

    // 생성된 공개키와 개인키 문자열을 여기에 복사합니다.
    private static final String PUBLIC_KEY_STR = "여기에_생성된_공개키_문자열_복사";
    private static final String PRIVATE_KEY_STR = "여기에_생성된_개인키_문자열_복사";

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public DigitalSignatureExample() throws Exception {
        byte[] publicBytes = Base64.getDecoder().decode(PUBLIC_KEY_STR);
        byte[] privateBytes = Base64.getDecoder().decode(PRIVATE_KEY_STR);

        // 공개키와 개인키를 생성할 때 X509EncodedKeySpec과 PKCS8EncodedKeySpec을 사용
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(publicBytes);
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(privateBytes);

        // RSA 알고리즘을 사용하는 KeyFactory 인스턴스 생성
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.publicKey = keyFactory.generatePublic(pubKeySpec);
        this.privateKey = keyFactory.generatePrivate(privKeySpec);
    }

    public String signMessage(String message) throws Exception {
        // SHA256withRSA 알고리즘을 사용하여 서명 객체 생성
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes("UTF-8"));
        byte[] digitalSignature = signature.sign();
        return Base64.getEncoder().encodeToString(digitalSignature);
    }

    public boolean verifySignature(String message, String signatureStr) throws Exception {
        // SHA256withRSA 알고리즘을 사용하여 서명 검증 객체 생성
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes("UTF-8"));
        byte[] digitalSignature = Base64.getDecoder().decode(signatureStr);
        return signature.verify(digitalSignature);
    }
}
