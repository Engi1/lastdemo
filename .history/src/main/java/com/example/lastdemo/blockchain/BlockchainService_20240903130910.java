package com.example.lastdemo.blockchain;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlockchainService {
    private List<Block> blockchain;

    public BlockchainService() {
        blockchain = new ArrayList<>();
        blockchain.add(createGenesisBlock());
    }

    public Block createGenesisBlock() {
        long timestamp = new Date().getTime();
        String hash = calculateHash(0, "0", timestamp, "Genesis Block");
        return new Block(0, "0", timestamp, "Genesis Block", hash);
    }

    public Block createNewBlock(String data) {
        Block previousBlock = getLatestBlock();
        int newIndex = previousBlock.getIndex() + 1;
        long timestamp = new Date().getTime();
        String hash = calculateHash(newIndex, previousBlock.getHash(), timestamp, data);
        Block newBlock = new Block(newIndex, previousBlock.getHash(), timestamp, data, hash);
        blockchain.add(newBlock);
        return newBlock;
    }

    public Block getLatestBlock() {
        return blockchain.get(blockchain.size() - 1);
    }

    public List<Block> getBlockchain() {
        return blockchain;
    }

    public boolean isChainValid() {
        for (int i = 1; i < blockchain.size(); i++) {
            Block currentBlock = blockchain.get(i);
            Block previousBlock = blockchain.get(i - 1);

            if (!currentBlock.getHash().equals(calculateHash(currentBlock.getIndex(), currentBlock.getPreviousHash(), currentBlock.getTimestamp(), currentBlock.getData()))) {
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    private String calculateHash(int index, String previousHash, long timestamp, String data) {
        String value = index + previousHash + timestamp + data;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(value.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
