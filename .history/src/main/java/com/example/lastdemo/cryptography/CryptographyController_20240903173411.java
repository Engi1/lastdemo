package com.example.lastdemo.cryptography;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cryptography")
public class CryptographyController {

    @PostMapping("/encrypt")
    public String encrypt(@RequestParam String message) throws Exception {
        SymmetricEncryptionExample example = new SymmetricEncryptionExample();
        return example.encryptMessage(message);
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestParam String encryptedMessage) throws Exception {
        SymmetricEncryptionExample example = new SymmetricEncryptionExample();
        return example.decryptMessage(encryptedMessage);
    }
}
