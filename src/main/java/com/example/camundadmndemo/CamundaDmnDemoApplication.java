package com.example.camundadmndemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamundaDmnDemoApplication implements CommandLineRunner {

    private final DMNService dmnService;

    public CamundaDmnDemoApplication(DMNService dmnService) {
        this.dmnService = dmnService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CamundaDmnDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dmnService.checkEligibilityForOperation("RETAIL", "ALL","372000","001");
    }
}
