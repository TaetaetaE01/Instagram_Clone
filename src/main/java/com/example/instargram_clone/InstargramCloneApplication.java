package com.example.instargram_clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class InstargramCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstargramCloneApplication.class, args);
    }

}
