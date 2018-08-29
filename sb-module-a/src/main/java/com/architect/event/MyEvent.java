package com.architect.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author wenxiong.jia
 * @since 2018/8/25
 */
public class MyEvent extends ApplicationContextEvent {
    /**
     * Create a new ContextStartedEvent.
     *
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public MyEvent(ApplicationContext source) {
        super(source);
    }

    public void print() {
        System.out.println("执行自定义事件方法。。。");
    }
}
