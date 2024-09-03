package com.example.lastdemo.cryptography;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SymmetricEncryptionExampleTest {

    @Test
    public void testSymmetricEncryption() throws Exception {
        SymmetricEncryptionExample example = new SymmetricEncryptionExample();
        
        String originalMessage = "Hello, this is a secret message!";
        String encryptedMessage = example.encryptMessage(originalMessage);
        String decryptedMessage = example.decryptMessage(encryptedMessage);

        assertEquals(originalMessage, decryptedMessage);
    }
}
