package com.django;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingApplication implements CommandLineRunner {

    public static void main(String [] args) {
        SpringApplication.run(ShoppingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.err.println("Shopping list app run ......");

    }
}
