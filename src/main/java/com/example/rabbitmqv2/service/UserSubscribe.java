package com.example.rabbitmqv2.service;

import com.example.rabbitmqv2.config.MessagingConfig;
import com.example.rabbitmqv2.controller.OrderPublisher;
import com.example.rabbitmqv2.dto.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserSubscribe {

    private final Logger logger = LoggerFactory.getLogger(UserSubscribe.class);
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus){
        logger.info("Message Consume From Queue : {}",orderStatus);

    }
}
