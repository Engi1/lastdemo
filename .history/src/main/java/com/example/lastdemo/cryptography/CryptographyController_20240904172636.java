package com.example.lastdemo.cryptography;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cryptography")
public class CryptographyController {

    // 대칭 암호화 (AES)
    @Operation(summary = "대칭 암호화 (AES)", description = "주어진 메시지를 AES 대칭 암호화 알고리즘으로 암호화합니다.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "암호화 성공", 
                                content = @Content(mediaType = "application/json", 
                                                   examples = @ExampleObject(value = "{\"encryptedMessage\": \"...\"}"))),
                   @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                   @ApiResponse(responseCode = "500", description = "서버 오류")
               })
    @PostMapping("/encrypt")
    public ResponseEntity<String> encrypt(@RequestParam("message") String message) {
        if (message == null || message.isEmpty()) {
            return ResponseEntity.badRequest().body("메시지는 비어 있을 수 없습니다.");
        }
        try {
            SymmetricEncryptionExample example = new SymmetricEncryptionExample();
            String encryptedMessage = example.encryptMessage(message);
            return ResponseEntity.ok(encryptedMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("암호화에 실패했습니다: " + e.getMessage());
        }
    }

    @Operation(summary = "대칭 복호화 (AES)", description = "암호화된 메시지를 AES 대칭 암호화 알고리즘으로 복호화합니다.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "복호화 성공", 
                                content = @Content(mediaType = "application/json", 
                                                   examples = @ExampleObject(value = "{\"decryptedMessage\": \"...\"}"))),
                   @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                   @ApiResponse(responseCode = "500", description = "서버 오류")
               })
    @PostMapping("/decrypt")
    public ResponseEntity<String> decrypt(@RequestParam("encryptedMessage") String encryptedMessage) {
        if (encryptedMessage == null || encryptedMessage.isEmpty()) {
            return ResponseEntity.badRequest().body("암호화된 메시지는 비어 있을 수 없습니다.");
        }
        try {
            SymmetricEncryptionExample example = new SymmetricEncryptionExample();
            String decryptedMessage = example.decryptMessage(encryptedMessage);
            return ResponseEntity.ok(decryptedMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("복호화에 실패했습니다: " + e.getMessage());
        }
    }

    // 비대칭 암호화 (RSA)
    @Operation(summary = "비대칭 암호화 (RSA)", description = "주어진 메시지를 RSA 비대칭 암호화 알고리즘으로 암호화합니다.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "암호화 성공", 
                                content = @Content(mediaType = "application/json", 
                                                   examples = @ExampleObject(value = "{\"encryptedMessage\": \"...\"}"))),
                   @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                   @ApiResponse(responseCode = "500", description = "서버 오류")
               })
    @PostMapping("/asymmetric/encrypt")
    public ResponseEntity<String> asymmetricEncrypt(@RequestParam("message") String message) {
        if (message == null || message.isEmpty()) {
            return ResponseEntity.badRequest().body("메시지는 비어 있을 수 없습니다.");
        }
        try {
            AsymmetricEncryptionExample example = new AsymmetricEncryptionExample();
            String encryptedMessage = example.encryptMessage(message);
            return ResponseEntity.ok(encryptedMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("암호화에 실패했습니다: " + e.getMessage());
        }
    }

    @Operation(summary = "비대칭 복호화 (RSA)", description = "암호화된 메시지를 RSA 비대칭 암호화 알고리즘으로 복호화합니다.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "복호화 성공", 
                                content = @Content(mediaType = "application/json", 
                                                   examples = @ExampleObject(value = "{\"decryptedMessage\": \"...\"}"))),
                   @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                   @ApiResponse(responseCode = "500", description = "서버 오류")
               })
    @PostMapping("/asymmetric/decrypt")
    public ResponseEntity<String> asymmetricDecrypt(@RequestParam("encryptedMessage") String encryptedMessage) {
        if (encryptedMessage == null || encryptedMessage.isEmpty()) {
            return ResponseEntity.badRequest().body("암호화된 메시지는 비어 있을 수 없습니다.");
        }
        try {
            AsymmetricEncryptionExample example = new AsymmetricEncryptionExample();
            String decryptedMessage = example.decryptMessage(encryptedMessage);
            return ResponseEntity.ok(decryptedMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("복호화에 실패했습니다: " + e.getMessage());
        }
    }

    // 해시 함수 (SHA-256)
    @Operation(summary = "해시 생성 (SHA-256)", description = "주어진 메시지에 대해 SHA-256 해시 값을 생성합니다.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "해시 생성 성공", 
                                content = @Content(mediaType = "application/json", 
                                                   examples = @ExampleObject(value = "{\"hash\": \"...\"}"))),
                   @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                   @ApiResponse(responseCode = "500", description = "서버 오류")
               })
    @PostMapping("/hash")
    public ResponseEntity<String> hash(@RequestParam("message") String message) {
        if (message == null || message.isEmpty()) {
            return ResponseEntity.badRequest().body("메시지는 비어 있을 수 없습니다.");
        }
        try {
            HashFunctionExample example = new HashFunctionExample();
            String hash = example.generateHash(message);
            return ResponseEntity.ok(hash);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("해시 생성에 실패했습니다: " + e.getMessage());
        }
    }

    // 디지털 서명 생성
    @Operation(summary = "디지털 서명 생성", description = "주어진 메시지에 대해 RSA를 이용한 디지털 서명을 생성합니다.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "서명 생성 성공", 
                                content = @Content(mediaType = "application/json", 
                                                   examples = @ExampleObject(value = "{\"signature\": \"...\"}"))),
                   @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                   @ApiResponse(responseCode = "500", description = "서버 오류")
               })
    @PostMapping("/sign")
    public ResponseEntity<Map<String, String>> sign(@RequestParam("message") String message) {
        if (message == null || message.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "메시지는 비어 있을 수 없습니다."));
        }
        try {
            DigitalSignatureExample example = new DigitalSignatureExample();
            String signature = example.signMessage(message);
            Map<String, String> response = new HashMap<>();
            response.put("message", message);
            response.put("signature", signature);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Collections.singletonMap("error", "서명 생성에 실패했습니다: " + e.getMessage()));
        }
    }

    // 디지털 서명 검증
    @Operation(summary = "디지털 서명 검증", description = "주어진 메시지와 서명을 이용해 디지털 서명을 검증합니다.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "서명 검증 성공", 
                                content = @Content(mediaType = "application/json", 
                                                   examples = @ExampleObject(value = "{\"valid\": true}"))),
                   @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                   @ApiResponse(responseCode = "500", description = "서버 오류")
               })
    @PostMapping("/verify")
    public ResponseEntity<Map<String, Boolean>> verify(@RequestParam("message") String message, 
                                                       @RequestParam("signature") String signature) {
        if (message == null || message.isEmpty() || signature == null || signature.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("valid", false));
        }
        try {
            DigitalSignatureExample example = new DigitalSignatureExample();
            boolean isValid = example.verifySignature(message, signature);
            return ResponseEntity.ok(Collections.singletonMap("valid", isValid));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Collections.singletonMap("valid", false));
        }
    }
}
