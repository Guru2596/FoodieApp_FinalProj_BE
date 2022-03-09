package com.niit.config;


import com.niit.rabbitmq.domain.DishDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;

    @Autowired
    public MessageProducer(RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    public void sendMessageToRabbitTemplate(DishDTO dishDTO){
        rabbitTemplate.convertAndSend(directExchange.getName(),"dishes_routing",dishDTO);
    }
}
