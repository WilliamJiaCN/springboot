package com.architect.listener;

import com.architect.event.MyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Service;

/**
 * @author wenxiong.jia
 * @since 2018/8/25
 */
@Service
public class MyListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof MyEvent) {
            MyEvent myEvent = (MyEvent) event;
            myEvent.print();
        }
        //容器关闭时触发
        if (event instanceof ContextClosedEvent) {
            System.out.println("容器关闭。。。");
        }
        //容器刷新时触发
        if (event instanceof ContextRefreshedEvent) {
            System.out.println("容器刷新。。。");
        }
        //容器启动时触发
        if (event instanceof ContextStartedEvent) {
            System.out.println("容器启动。。。");
        }
        //容器停止时触发
        if (event instanceof ContextStoppedEvent) {
            System.out.println("容器停止。。。");
        }
    }
}
