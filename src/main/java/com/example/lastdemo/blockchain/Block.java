package com.example.lastdemo.blockchain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Block {
    private int index;
    private String previousHash;
    private long timestamp;
    private String data;
    private String hash;
}
