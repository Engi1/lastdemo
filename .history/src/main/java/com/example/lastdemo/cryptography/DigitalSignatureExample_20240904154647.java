package com.example.lastdemo.cryptography;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature; // 올바른 Signature 클래스 임포트
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DigitalSignatureExample {

    private static final String PUBLIC_KEY_STR = "your_base64_encoded_public_key_here";
    private static final String PRIVATE_KEY_STR = "your_base64_encoded_private_key_here";

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public DigitalSignatureExample() throws Exception {
        byte[] publicBytes = Base64.getDecoder().decode(PUBLIC_KEY_STR);
        byte[] privateBytes = Base64.getDecoder().decode(PRIVATE_KEY_STR);

        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(publicBytes);
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(privateBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.publicKey = keyFactory.generatePublic(pubKeySpec);
        this.privateKey = keyFactory.generatePrivate(privKeySpec);
    }

    public String signMessage(String message) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA"); // 올바른 Signature 사용
        signature.initSign(privateKey);
        signature.update(message.getBytes("UTF-8"));
        byte[] digitalSignature = signature.sign();
        return Base64.getEncoder().encodeToString(digitalSignature);
    }

    public boolean verifySignature(String message, String signatureStr) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA"); // 올바른 Signature 사용
        signature.initVerify(publicKey);
        signature.update(message.getBytes("UTF-8"));
        byte[] digitalSignature = Base64.getDecoder().decode(signatureStr);
        return signature.verify(digitalSignature);
    }
}
