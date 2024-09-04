package com.example.lastdemo.cryptography;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cryptography")
public class CryptographyController {

    private static final String PUBLIC_KEY_STR = "여기에_생성된_공개키_문자열_복사";
    private static final String PRIVATE_KEY_STR = "여기에_생성된_개인키_문자열_복사";

    private final DigitalSignatureExample digitalSignatureExample;

    public CryptographyController() throws Exception {
        // 예외 처리 추가
        DigitalSignatureExample tempExample;
        try {
            tempExample = new DigitalSignatureExample(PUBLIC_KEY_STR, PRIVATE_KEY_STR);
        } catch (Exception e) {
            // 예외를 잡아 로그를 출력하고 예외를 다시 던집니다.
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize DigitalSignatureExample", e);
        }
        this.digitalSignatureExample = tempExample;
    }

    // 대칭 암호화 (AES)
    @Operation(summary = "대칭 암호화 (AES)", description = "주어진 메시지를 AES 대칭 암호화 알고리즘으로 암호화합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "암호화 성공"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/encrypt")
    public String encrypt(@RequestParam("message") String message) throws Exception {
        SymmetricEncryptionExample example = new SymmetricEncryptionExample();
        return example.encryptMessage(message);
    }

    @Operation(summary = "대칭 복호화 (AES)", description = "암호화된 메시지를 AES 대칭 암호화 알고리즘으로 복호화합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "복호화 성공"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/decrypt")
    public String decrypt(@RequestParam("encryptedMessage") String encryptedMessage) throws Exception {
        SymmetricEncryptionExample example = new SymmetricEncryptionExample();
        return example.decryptMessage(encryptedMessage);
    }

    // 비대칭 암호화 (RSA)
    @Operation(summary = "비대칭 암호화 (RSA)", description = "주어진 메시지를 RSA 비대칭 암호화 알고리즘으로 암호화합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "암호화 성공"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/asymmetric/encrypt")
    public String asymmetricEncrypt(@RequestParam("message") String message) throws Exception {
        AsymmetricEncryptionExample example = new AsymmetricEncryptionExample();
        return example.encryptMessage(message);
    }

    @Operation(summary = "비대칭 복호화 (RSA)", description = "암호화된 메시지를 RSA 비대칭 암호화 알고리즘으로 복호화합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "복호화 성공"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/asymmetric/decrypt")
    public String asymmetricDecrypt(@RequestParam("encryptedMessage") String encryptedMessage) throws Exception {
        AsymmetricEncryptionExample example = new AsymmetricEncryptionExample();
        return example.decryptMessage(encryptedMessage);
    }

    // 해시 함수 (SHA-256)
    @Operation(summary = "해시 생성 (SHA-256)", description = "주어진 메시지에 대해 SHA-256 해시 값을 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "해시 생성 성공"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/hash")
    public String hash(@RequestParam("message") String message) throws Exception {
        HashFunctionExample example = new HashFunctionExample();
        return example.generateHash(message);
    }

    @Operation(summary = "디지털 서명 생성", description = "주어진 메시지에 대해 RSA 알고리즘으로 디지털 서명을 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "서명 생성 성공"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/sign")
    public String sign(@RequestParam("message") String message) throws Exception {
        if (digitalSignatureExample == null) {
            throw new IllegalStateException("DigitalSignatureExample is not initialized properly.");
        }
        return digitalSignatureExample.signMessage(message);
    }

    @Operation(summary = "디지털 서명 검증", description = "주어진 메시지와 서명을 사용해 RSA 알고리즘으로 서명을 검증합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "서명 검증 성공"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/verify")
    public boolean verify(@RequestParam("message") String message, @RequestParam("signature") String signature) throws Exception {
        if (digitalSignatureExample == null) {
            throw new IllegalStateException("DigitalSignatureExample is not initialized properly.");
        }
        return digitalSignatureExample.verifySignature(message, signature);
    }



}
