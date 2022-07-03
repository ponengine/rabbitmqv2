package com.example.rabbitmqv2.controller;

import com.example.rabbitmqv2.config.MessagingConfig;
import com.example.rabbitmqv2.dto.Order;
import com.example.rabbitmqv2.dto.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    private final Logger logger = LoggerFactory.getLogger(OrderPublisher.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/bookorder/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName){
        logger.info("Book order with Exchange :{} , Routing key:{} , Restaurant name:{} ",MessagingConfig.EXCHANGE,MessagingConfig.ROUTING_KEY,restaurantName);
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus=new OrderStatus(order,"PROCESS","order placed successfully in "+restaurantName);
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE,MessagingConfig.ROUTING_KEY,orderStatus);
        return "Success!!";
    }
}
