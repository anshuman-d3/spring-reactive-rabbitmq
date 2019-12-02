package com.supernow.springrabbitmqwebflux.controller;

import com.supernow.springrabbitmqwebflux.model.ProcessExecution;
import com.supernow.springrabbitmqwebflux.service.RabbitMQReceiver;
import com.supernow.springrabbitmqwebflux.service.RabbitMQSender;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RabbitListener(queues = "supernow.queue")
public class BaseController {

    @Autowired
    RabbitMQSender rabbitMQSender;

    @Autowired
    RabbitMQReceiver rabbitMQReceiver;

    @GetMapping(value = "/update-status")
    public String updateStatus(@RequestParam String processId, @RequestParam String status) {
        ProcessExecution processExecution = new ProcessExecution();
        processExecution.setProcessId(processId);
        processExecution.setStatus(status);
        rabbitMQSender.send(processExecution);
        return "Status Sent to RabbitMQ";
    }

    @GetMapping(value = "/fetch-status")
    public Flux<String> fetchStatus() {
        return rabbitMQReceiver.sseFlux;
    }

}
