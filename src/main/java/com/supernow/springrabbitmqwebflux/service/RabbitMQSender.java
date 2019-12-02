package com.supernow.springrabbitmqwebflux.service;

import com.supernow.springrabbitmqwebflux.model.ProcessExecution;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${supernow.rabbitmq.test-exchange}")
    private String exchange;

    @Value("${supernow.rabbitmq.test-routing-key}")
    private String routingkey;

    public void send(ProcessExecution processExecution) {
        rabbitTemplate.convertAndSend(exchange, routingkey, processExecution);
    }


}
