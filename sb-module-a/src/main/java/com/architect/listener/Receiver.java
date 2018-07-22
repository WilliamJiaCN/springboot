package com.architect.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wenxiong.jia
 * @since 2018/7/21
 */
@Component
@RabbitListener(queues = "test")
public class Receiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("receive msg：" + msg);
    }
}
