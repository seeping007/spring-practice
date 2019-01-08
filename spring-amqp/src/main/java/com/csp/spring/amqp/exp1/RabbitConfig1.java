package com.csp.spring.amqp.exp1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CSP
 * @since 2019/1/8
 */
@Configuration
public class RabbitConfig1 {

    @Bean(name = "exp1Queue")
    public Queue helloQueue() {
        return new Queue("hello");
    }

    private static class ReceiverConfig {

        @Bean
        public Exp1Receiver receiver1() {
            return new Exp1Receiver(1);
        }

        @Bean
        public Exp1Receiver receiver2() {
            return new Exp1Receiver(2);
        }
    }
}
