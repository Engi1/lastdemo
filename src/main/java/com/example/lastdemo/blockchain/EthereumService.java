package com.example.lastdemo.blockchain;

import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

@Service
public class EthereumService {

    private Web3j web3j;

    public EthereumService() {
        // Infura 프로젝트 ID를 사용하여 이더리움 메인넷에 연결
        web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/YOUR_INFURA_PROJECT_ID"));
    }

    public String getClientVersion() {
        try {
            Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
            return web3ClientVersion.getWeb3ClientVersion();
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to Ethereum client", e);
        }
    }
}
