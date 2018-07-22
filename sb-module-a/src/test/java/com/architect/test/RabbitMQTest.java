package com.architect.test;

import com.architect.service.Sender;
import com.architect.startup.SpringBootStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wenxiong.jia
 * @since 2018/7/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStart.class)
public class RabbitMQTest {

    @Autowired
    private Sender sender;

    @Test
    public void testSend() {
        sender.send();
    }
}
