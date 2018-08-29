package com.architect.test;

import com.architect.event.MyEvent;
import com.architect.startup.SpringBootStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wenxiong.jia
 * @since 2018/7/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStart.class)
public class EventTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testPublishEvent() {
        MyEvent myEvent = new MyEvent(applicationContext);
        applicationContext.publishEvent(myEvent);
        System.out.println("发布事件成功!!!");
    }
}
