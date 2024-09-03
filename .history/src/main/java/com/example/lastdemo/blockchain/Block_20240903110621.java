package com.example.yourdemo.blockchain;

import java.util.Date;

public class Block {
    private int index;
    private String previousHash;
    private long timestamp;
    private String data;
    private String hash;

    public Block(int index, String previousHash, long timestamp, String data, String hash) {
        this.index = index;
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.data = data;
        this.hash = hash;
    }

    public int getIndex() {
        return index;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }

    public String getHash() {
        return hash;
    }
}
