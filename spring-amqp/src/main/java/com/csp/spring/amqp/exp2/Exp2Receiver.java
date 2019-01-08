package com.csp.spring.amqp.exp2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * @author CSP
 * @since 2019/1/8
 */
@Slf4j
public class Exp2Receiver {

    @RabbitListener(queues = "#{exp2Queue1.name}")
    public void receive1(String in) throws InterruptedException {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{exp2Queue2.name}")
    public void receive2(String in) throws InterruptedException {
        receive(in, 2);
    }

    private void receive(String in, int receiver) throws InterruptedException {

        StopWatch watch = new StopWatch();
        watch.start();

        log.info("[Receive] Instance {}, received: {}", receiver, in);
        doWork(in);

        watch.stop();
        log.info("[Receive] Instance {}, done in {} s", receiver, watch.getTotalTimeSeconds());
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}
