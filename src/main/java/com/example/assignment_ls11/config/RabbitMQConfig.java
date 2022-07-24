package com.example.assignment_ls11.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Create Consume
@Configuration
public class RabbitMQConfig {

    public static String QUEUE_NAME = "demo_queue";
    public static String EXCHANGE_NAME = "demo_exchange_queue";
    public static String ROUTING_KEY = "route_demo";

    // Create new Queue
    @Bean
    Queue queue() {
        return new Queue(RabbitMQConfig.QUEUE_NAME, false);
    }

    // Creat Direct Exchange
    @Bean
    DirectExchange exchange(){
        return new DirectExchange(RabbitMQConfig.EXCHANGE_NAME);
    }

    // Creat new Binding and bind Exchange vs Queue
    @Bean
    Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMQConfig.ROUTING_KEY);
    }

    // Create Consumer getting data from Queue
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        // ConnectionFactory automatically maps with application.properties to get arguments
        container.setConnectionFactory(connectionFactory);
        // Set queue for Consumer
        container.setQueueNames(RabbitMQConfig.QUEUE_NAME);

        // Get Message from Queue and process in class Receiver
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
    return new MessageListenerAdapter(receiver, "MessageCineflix");
    }
}
