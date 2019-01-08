package com.csp.spring.amqp.exp1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * Receive messages from a queue
 *
 * @author CSP
 * @since 2019/1/8
 */
@Slf4j
@RabbitListener(queues = "hello")
public class Exp1Receiver {

    private final int instance;

    public Exp1Receiver(int i) {
        this.instance = i;
    }

    @RabbitHandler
    public void receive(String in) throws InterruptedException {

        StopWatch watch = new StopWatch();
        watch.start();

        log.info("[Receiver] instance {}, received: {}", instance, in);
        doWork(in);

        watch.stop();
        log.info("[Receiver] instance {}, done in {} s", instance, watch.getTotalTimeSeconds());
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}
