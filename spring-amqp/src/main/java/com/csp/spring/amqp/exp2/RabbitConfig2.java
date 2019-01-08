package com.csp.spring.amqp.exp2;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CSP
 * @since 2019/1/8
 */
@Configuration
public class RabbitConfig2 {

    /* fanout exchange:
     * it broadcasts all the messages it receives to all the queues it knows.
     */
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("exp2.fanout");
    }

    private static class ReceiverConfig {

        @Bean(name = "exp2Queue1")
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean(name = "exp2Queue2")
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1(FanoutExchange fanout, @Qualifier("exp2Queue1") Queue queue1) {
            return BindingBuilder.bind(queue1).to(fanout);
        }

        @Bean
        public Binding binding2(FanoutExchange fanout, @Qualifier("exp2Queue2") Queue queue2) {
            return BindingBuilder.bind(queue2).to(fanout);
        }

        @Bean
        public Exp2Receiver receiver() {
            return new Exp2Receiver();
        }
    }
}
