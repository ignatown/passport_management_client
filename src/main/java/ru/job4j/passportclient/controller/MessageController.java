package ru.job4j.passportclient.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageController {

    @KafkaListener(topics = {"unavailable"})
    public void listenUnavailable(ConsumerRecord<Integer, String> input) {
        System.out.println("sending messages");
        System.out.println(input.value());
    }

}