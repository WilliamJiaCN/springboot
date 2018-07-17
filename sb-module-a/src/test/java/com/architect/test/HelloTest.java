package com.architect.test;

import com.architect.controller.HelloController;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author wenxiong.jia
 * @since 2018/7/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HelloController.class)
@WebAppConfiguration
public class HelloTest {

    @Autowired
    private HelloController helloController;

    @Test
    public void testHello() {
        TestCase.assertEquals("Hello World!", helloController.home());
        System.out.println(helloController.home());
    }
}
