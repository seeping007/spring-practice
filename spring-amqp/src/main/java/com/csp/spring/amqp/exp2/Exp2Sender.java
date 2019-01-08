package com.csp.spring.amqp.exp2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author CSP
 * @since 2019/1/8
 */
@Slf4j
@Component
public class Exp2Sender {

    private final RabbitTemplate rabbitTemplate;

    private final FanoutExchange fanout;

    private AtomicInteger dots = new AtomicInteger(0);
    private AtomicInteger count = new AtomicInteger(0);

    @Autowired
    public Exp2Sender(RabbitTemplate rabbitTemplate, FanoutExchange fanout) {
        this.rabbitTemplate = rabbitTemplate;
        this.fanout = fanout;
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
         * routingKey: its value is ignored for fanout exchange
         */
        rabbitTemplate.convertAndSend(fanout.getName(), "", msg);
        log.info("[Send] Sent: {}", msg);
    }
}
