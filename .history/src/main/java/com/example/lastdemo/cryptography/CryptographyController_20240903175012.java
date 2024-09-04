package com.example.lastdemo.cryptography;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cryptography")
public class CryptographyController {

    // 대칭 암호화 (AES)
    @PostMapping("/encrypt")
    public String encrypt(@RequestParam("message") String message) throws Exception {
        SymmetricEncryptionExample example = new SymmetricEncryptionExample();
        return example.encryptMessage(message);
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestParam("encryptedMessage") String encryptedMessage) throws Exception {
        SymmetricEncryptionExample example = new SymmetricEncryptionExample();
        return example.decryptMessage(encryptedMessage);
    }

    // 비대칭 암호화 (RSA)
    @PostMapping("/asymmetric/encrypt")
    public String asymmetricEncrypt(@RequestParam("message") String message) throws Exception {
        AsymmetricEncryptionExample example = new AsymmetricEncryptionExample();
        return example.encryptMessage(message);
    }

    @PostMapping("/asymmetric/decrypt")
    public String asymmetricDecrypt(@RequestParam("encryptedMessage") String encryptedMessage) throws Exception {
        AsymmetricEncryptionExample example = new AsymmetricEncryptionExample();
        return example.decryptMessage(encryptedMessage);
    }

    // 해시 함수 (SHA-256)
    @PostMapping("/hash")
    public String hash(@RequestParam("message") String message) throws Exception {
        HashFunctionExample example = new HashFunctionExample();
        return example.generateHash(message);
    }

    // 디지털 서명 (RSA 서명)
    @PostMapping("/sign")
    public String sign(@RequestParam("message") String message) throws Exception {
        DigitalSignatureExample example = new DigitalSignatureExample();
        return example.signMessage(message);
    }

    @PostMapping("/verify")
    public boolean verify(@RequestParam("message") String message, @RequestParam("signature") String signature) throws Exception {
        DigitalSignatureExample example = new DigitalSignatureExample();
        return example.verifySignature(message, signature);
    }
}
