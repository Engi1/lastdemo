package com.example.lastdemo.blockchain;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blockchain")
@CrossOrigin(origins = "http://localhost:3000")
public class BlockchainController {

    @Autowired
    private BlockchainService blockchainService;

    @Autowired
    private EthereumService ethereumService;

    @Operation(summary = "전체 블록체인 조회", description = "블록체인에 있는 모든 블록을 리스트 형태로 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "블록체인 조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping
    public List<Block> getBlockchain() {
        return blockchainService.getBlockchain();
    }

    @Operation(summary = "새 블록 생성", description = "입력된 데이터를 사용하여 새로운 블록을 블록체인에 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "블록 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 데이터 입력"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping
    public Block createBlock(@RequestBody String data) {
        return blockchainService.createNewBlock(data);
    }

    @Operation(summary = "블록체인 유효성 검사", description = "현재 블록체인이 유효한지 확인합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "블록체인 유효성 검사 성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/isValid")
    public boolean isBlockchainValid() {
        return blockchainService.isChainValid();
    }

    @Operation(summary = "이더리움 클라이언트 버전 조회", description = "연결된 이더리움 노드의 클라이언트 버전을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이더리움 클라이언트 버전 조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/ethereum/client-version")
    public String getEthereumClientVersion() {
        return ethereumService.getClientVersion();
    }
}
