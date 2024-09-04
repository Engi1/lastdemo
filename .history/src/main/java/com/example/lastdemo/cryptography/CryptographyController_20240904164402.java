package com.example.lastdemo.cryptography;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cryptography")
public class CryptographyController {

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

    // 디지털 서명 생성
    @Operation(summary = "디지털 서명 생성", description = "주어진 메시지에 대해 RSA를 이용한 디지털 서명을 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "서명 생성 성공"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/sign")
    public String sign(@RequestParam("message") String message) throws Exception {
        DigitalSignatureExample example = new DigitalSignatureExample();
        return example.signMessage(message);
    }

    // 디지털 서명 검증
    @Operation(summary = "디지털 서명 검증", description = "주어진 메시지와 서명을 이용해 디지털 서명을 검증합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "서명 검증 성공"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/verify")
    public boolean verify(@RequestParam("message") String message, @RequestParam("signature") String signature) throws Exception {
        DigitalSignatureExample example = new DigitalSignatureExample();
        return example.verifySignature(message, signature);
    }
}



}
