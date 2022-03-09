package com.niit.config;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

    private static final String registerQueue = "dishes_queue";
    private static final String exchangeName = "dishes_exchange";
    private static final String dishes_routing = "dishes_routing";

    @Bean
    public Queue registerQueue(){
        return new Queue(registerQueue,false);
    }

    //simple container collecting information to describe a direct exchange. Used in conjunction with administrative operations.
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(exchangeName);
    }

    //JSON converter that uses the Jackson 2 Json library.
    @Bean
    public Jackson2JsonMessageConverter producerJackson2JsonConverter(){
        return new Jackson2JsonMessageConverter();
    }

//
//    public interface ConnectionFactory
//An interface based ConnectionFactory for creating Connections.
    //RabbitTemplate ->Helper class that simplifies synchronous RabbitMQ access (sending and receiving messages).

    @Bean
    public RabbitTemplate rabbitTemplate (ConnectionFactory connectionFactory){
    RabbitTemplate rabbitTemp = new RabbitTemplate(connectionFactory);
    rabbitTemp.setMessageConverter(producerJackson2JsonConverter());
    return rabbitTemp;
    }

    @Bean
    public Binding bindingCustomer (Queue registerQueue, DirectExchange directExchange){

        return BindingBuilder.bind(registerQueue).to(directExchange).with(dishes_routing);
    }

}
