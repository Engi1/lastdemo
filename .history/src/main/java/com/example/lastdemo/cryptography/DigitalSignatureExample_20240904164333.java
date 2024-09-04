package com.example.lastdemo.cryptography;

import java.security.*;
import java.util.Base64;

public class DigitalSignatureExample {

    private KeyPair keyPair;

    public DigitalSignatureExample() throws NoSuchAlgorithmException {
        this.keyPair = generateKeyPair();
    }

    // RSA 키 쌍 생성
    private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        return keyPairGen.generateKeyPair();
    }

    // 메시지에 대한 디지털 서명 생성
    public String signMessage(String message) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(keyPair.getPrivate());
        signature.update(message.getBytes());
        byte[] digitalSignature = signature.sign();
        return Base64.getEncoder().encodeToString(digitalSignature);
    }

    // 디지털 서명 검증
    public boolean verifySignature(String message, String signatureStr) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(keyPair.getPublic());
        signature.update(message.getBytes());
        byte[] digitalSignature = Base64.getDecoder().decode(signatureStr);
        return signature.verify(digitalSignature);
    }

    // 공개 키 가져오기 (데모용)
    public String getPublicKey() {
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }
}
