package com.example.lastdemo.cryptography;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class DigitalSignatureExample {

    public static void main(String[] args) throws Exception {
        // RSA 키 쌍 생성
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // 원본 메시지
        String originalMessage = "Hello, this is a signed message!";

        // 디지털 서명 생성 (개인 키 사용)
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(originalMessage.getBytes());
        byte[] digitalSignature = signature.sign();
        String signedMessage = Base64.getEncoder().encodeToString(digitalSignature);
        System.out.println("Digital Signature: " + signedMessage);

        // 디지털 서명 검증 (공개 키 사용)
        Signature signatureVerify = Signature.getInstance("SHA256withRSA");
        signatureVerify.initVerify(publicKey);
        signatureVerify.update(originalMessage.getBytes());
        boolean isVerified = signatureVerify.verify(Base64.getDecoder().decode(signedMessage));
        System.out.println("Signature Verified: " + isVerified);
    }
}
