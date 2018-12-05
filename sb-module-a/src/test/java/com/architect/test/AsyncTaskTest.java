package com.architect.test;

import com.architect.service.AsyncTask;
import com.architect.startup.SpringBootStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author wenxiong.jia
 * @since 2018/11/4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStart.class)
public class AsyncTaskTest {
    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void asyncTaskTest() throws IOException {
        System.out.println("Async method start.");
        for (int i = 0; i < 10; i++) {
            asyncTask.doTask1(i);
            asyncTask.doTask2(i);
        }
        System.out.println("All tasks finished.");
        System.in.read();
    }
}
