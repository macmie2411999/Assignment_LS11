package com.example.assignment_ls11.controller;

import com.example.assignment_ls11.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class DemoController {

//    RabbitTemplate rabbitTemplate = new RabbitTemplate();

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")

    @Autowired
    RabbitTemplate rabbitTemplate;

    // When user go to this url, a message is sent to Queue, then Receiver gets that message and processes it
    @GetMapping("/home")
    public void sendQueue(){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                "Hello Queueeeee!");
    }
}
