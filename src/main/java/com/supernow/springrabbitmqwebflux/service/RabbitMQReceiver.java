package com.supernow.springrabbitmqwebflux.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

@Service
public class RabbitMQReceiver {

    private final UnicastProcessor<String> sseFluxProcessor = UnicastProcessor.create();

    public final Flux<String> sseFlux = this.sseFluxProcessor.share();

    @RabbitListener(queues = "supernow.queue")
    private void getProcessExecutionUpdate(Message message) {
        this.sseFluxProcessor.onNext(new String(message.getBody()));
    }

}
