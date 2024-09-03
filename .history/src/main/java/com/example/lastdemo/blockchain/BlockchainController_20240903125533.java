package com.example.lastdemo.blockchain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blockchain")
@CrossOrigin(origins = "http://localhost:3000")
public class BlockchainController {

    @Autowired
    private BlockchainService blockchainService;

    @GetMapping
    public List<Block> getBlockchain() {
        return blockchainService.getBlockchain();
    }

    @PostMapping
    public Block createBlock(@RequestBody String data) {
        return blockchainService.createNewBlock(data);
    }
}
