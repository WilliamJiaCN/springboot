package com.architect.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author wenxiong.jia
 * @since 2018/11/4
 */
@Slf4j
@Service
public class AsyncTask {
    /**
     * myTaskAsynPool即配置线程池的方法名，
     * 此处如果不写自定义线程池的方法名，
     * 会使用默认的线程池
     *
     * @param i
     * @throws InterruptedException
     */
    @Async("myTask1AsyncPool")
    public void doTask1(int i) {
        System.out.println(Thread.currentThread().getName() + "-Task1" + i + " started.");
    }

    /**
     * @param i
     * @throws InterruptedException
     */
    @Async
    public void doTask2(int i) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-Task2" + i + " started.");
    }
}
