package com.rakuten.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CharacterManagementFrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharacterManagementFrontendApplication.class, args);
    }

}
