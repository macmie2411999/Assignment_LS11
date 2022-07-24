package com.example.assignment_ls11.config;

import org.springframework.stereotype.Component;

@Component
public class Receiver {
    public void receiveMessage(String message){
        System.out.println("Message from Queue: " + message);
    }
}
