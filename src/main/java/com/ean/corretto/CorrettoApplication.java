package com.ean.corretto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CorrettoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorrettoApplication.class, args);
    }

}