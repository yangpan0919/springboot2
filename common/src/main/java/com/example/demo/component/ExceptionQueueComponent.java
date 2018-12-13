package com.example.demo.component;

import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * <p>异常信息队列</p>
 */
@Component(value = "exceptionQueueComponent")
public class ExceptionQueueComponent {

    private static final BlockingQueue<Exception> EXCEPTION_QUEUE = new ArrayBlockingQueue<Exception>(100);

    public BlockingQueue<Exception> getBlockingQueue(){
        return EXCEPTION_QUEUE;
    }

    public void put(Exception ex) throws Exception {
        EXCEPTION_QUEUE.add(ex);
    }

    public Exception take() throws Exception {
        return EXCEPTION_QUEUE.take();
    }

    public Exception remove(){
        return EXCEPTION_QUEUE.remove();
    }
}
