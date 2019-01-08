package com.csp.spring.amqp.exp1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Send message to a queue (using default exchange)
 *
 * @author CSP
 * @since 2019/1/8
 */
@Slf4j
@Component
public class Exp1Sender {

    private final RabbitTemplate rabbitTemplate;

    private final Queue queue;

    private AtomicInteger dots = new AtomicInteger(0);
    private AtomicInteger count = new AtomicInteger(0);

    @Autowired
    public Exp1Sender(RabbitTemplate rabbitTemplate, @Qualifier("exp1Queue") Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void send(String name) {

        StringBuilder builder = new StringBuilder(name);
        if (dots.incrementAndGet() == 3) {
            dots.set(1);
        }

        for (int i = 0; i < dots.get(); i++) {
            builder.append(".");
        }
        builder.append(count.incrementAndGet());
        String msg = builder.toString();

        /*
         * Each queue is automatically bound to the default exchange with the name of queue as the binding key.
         * This is why we can use the name of the queue as the routing key to make sure the message ends up in the queue.
         */
        rabbitTemplate.convertAndSend(queue.getName(), msg);
        log.info("[Sender] Send: {}", msg);
    }
}
