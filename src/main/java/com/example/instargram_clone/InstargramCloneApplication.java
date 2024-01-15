package com.example.instargram_clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InstargramCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstargramCloneApplication.class, args);
    }

}
