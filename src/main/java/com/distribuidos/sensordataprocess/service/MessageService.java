package com.distribuidos.sensordataprocess.service;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {

    private final AmqpTemplate amqpTemplate;
    private final String queueName = "sensor-test";

    public void sendMessage(String mensaje) {
        amqpTemplate.convertAndSend(queueName, mensaje);
        System.out.println("Mensaje enviado: " + mensaje);
    }
}

