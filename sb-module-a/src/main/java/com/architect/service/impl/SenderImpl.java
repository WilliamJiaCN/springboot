package com.architect.service.impl;

import com.architect.service.Sender;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wenxiong.jia
 * @since 2018/7/21
 */
@Service
public class SenderImpl implements Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void send() {
        amqpTemplate.convertAndSend("test", "test rabbitmq message!!!!!!!!!");
    }
}
