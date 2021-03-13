package org.example.feign;

import org.springframework.stereotype.Component;

@Component
public class Bank2ClientFallback implements Bank2Client {

    @Override
    public Boolean transfer(Double amount) {

        return false;
    }

    @Override
    public String hello() {
        return "bank2 宕机了！";
    }
}