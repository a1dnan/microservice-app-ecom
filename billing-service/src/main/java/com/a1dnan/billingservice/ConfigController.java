package com.a1dnan.billingservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequiredArgsConstructor
public class ConfigController {

        private final MyConsulConfig consulConfig;
        private final MyVaultConfig vaultConfig;
//    @Value("${token.accessTokenTimeout}")
//    private Long accessTokenTimeout;
//    @Value("${token.refreshTokenTimeout}")
//    private Long refreshTokenTimeout;


    @GetMapping("/myConfig")
    public Map<String, Object> getConfig(){
        return Map.of("consulConfig", consulConfig, "vaultConfig", vaultConfig);
    }
}
