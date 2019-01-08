package com.csp.spring.amqp.controller;

import com.csp.spring.amqp.exp1.Exp1Sender;
import com.csp.spring.amqp.exp2.Exp2Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CSP
 * @since 2019/1/8
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    private final Exp1Sender exp1Sender;

    private final Exp2Sender exp2Sender;

    @Autowired
    public RabbitController(Exp1Sender exp1Sender, Exp2Sender exp2Sender) {
        this.exp1Sender = exp1Sender;
        this.exp2Sender = exp2Sender;
    }

    @RequestMapping(value = "/exp1/{name}", method = RequestMethod.GET)
    public Object exp1Send(@PathVariable String name) {
        exp1Sender.send(name);
        return true;
    }

    @RequestMapping(value = "/exp2/{name}", method = RequestMethod.GET)
    public Object exp2Send(@PathVariable String name) {
        exp2Sender.send(name);
        return true;
    }
}
