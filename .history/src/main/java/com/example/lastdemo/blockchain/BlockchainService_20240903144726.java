package com.example.lastdemo.blockchain;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlockchainService {
    private List<Block> blockchain;  // 블록체인 데이터를 저장하는 리스트

    public BlockchainService() {
        // 생성자: 블록체인 초기화 및 제네시스 블록 생성
        blockchain = new ArrayList<>();
        blockchain.add(createGenesisBlock());  // 제네시스 블록을 블록체인에 추가
    }

    public Block createGenesisBlock() {
        // 제네시스 블록 생성 메서드
        long timestamp = new Date().getTime();  // 현재 시간의 타임스탬프를 가져옴
        String hash = calculateHash(0, "0", timestamp, "Genesis Block");  // 제네시스 블록의 해시 계산
        return new Block(0, "0", timestamp, "Genesis Block", hash);  // 제네시스 블록 생성 후 반환
    }

    public Block createNewBlock(String data) {
        // 새로운 블록 생성 메서드
        Block previousBlock = getLatestBlock();  // 이전 블록(가장 최신 블록) 가져오기
        int newIndex = previousBlock.getIndex() + 1;  // 새로운 블록의 인덱스는 이전 블록의 인덱스 + 1
        long timestamp = new Date().getTime();  // 현재 시간의 타임스탬프를 가져옴
        String hash = calculateHash(newIndex, previousBlock.getHash(), timestamp, data);  // 새로운 블록의 해시 계산
        Block newBlock = new Block(newIndex, previousBlock.getHash(), timestamp, data, hash);  // 새로운 블록 생성
        blockchain.add(newBlock);  // 블록체인에 새로운 블록 추가
        return newBlock;  // 생성된 새로운 블록 반환
    }

    public Block getLatestBlock() {
        // 가장 최신의 블록을 반환하는 메서드
        return blockchain.get(blockchain.size() - 1);  // 리스트의 마지막 블록 반환
    }

    public List<Block> getBlockchain() {
        // 블록체인 전체를 반환하는 메서드
        return blockchain;  // 블록체인 리스트 반환
    }

    public boolean isChainValid() {
        // 블록체인의 유효성을 검사하는 메서드
        for (int i = 1; i < blockchain.size(); i++) {
            Block currentBlock = blockchain.get(i);  // 현재 블록 가져오기
            Block previousBlock = blockchain.get(i - 1);  // 이전 블록 가져오기

            // 현재 블록의 해시가 올바른지 확인
            if (!currentBlock.getHash().equals(calculateHash(currentBlock.getIndex(), currentBlock.getPreviousHash(), currentBlock.getTimestamp(), currentBlock.getData()))) {
                return false;  // 해시가 올바르지 않으면 유효하지 않음
            }

            // 현재 블록의 previousHash가 이전 블록의 해시와 일치하는지 확인
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;  // 일치하지 않으면 유효하지 않음
            }
        }
        return true;  // 모든 블록이 유효하면 true 반환
    }

    private String calculateHash(int index, String previousHash, long timestamp, String data) {
        // 블록의 해시를 계산하는 메서드
        String value = index + previousHash + timestamp + data;  // 해시 계산을 위한 입력 값 생성
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");  // SHA-256 해시 알고리즘 사용
            byte[] hash = digest.digest(value.getBytes("UTF-8"));  // 입력 값을 해시하여 바이트 배열로 변환
            StringBuilder hexString = new StringBuilder();  // 해시 값을 16진수 문자열로 변환하기 위한 StringBuilder
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);  // 바이트 값을 16진수로 변환
                if (hex.length() == 1) hexString.append('0');  // 한 자릿수일 경우 앞에 '0'을 추가하여 두 자릿수로 맞춤
                hexString.append(hex);  // 16진수 문자열을 StringBuilder에 추가
            }
            return hexString.toString();  // 최종 해시 문자열 반환
        } catch (Exception e) {
            throw new RuntimeException(e);  // 해시 계산 중 예외 발생 시 런타임 예외 발생
        }
    }
}
