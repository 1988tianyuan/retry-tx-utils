package com.liugeng.retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.DelayQueue;

@Component
public class Test {

    @Autowired
    private ThreadPoolTaskExecutor executor;
    private DelayQueue<DelayTask<Result>> delayQueue = new DelayQueue<>();
    private DelayProducer producer;
    private DelayConsumer consumer;

    @PostConstruct
    public void initProducerAndConsumer() {
        producer = new DelayProducer(delayQueue);
        consumer = new DelayConsumer(delayQueue, executor);
    }

    public void doDelayTask() {
        producer.produce();
        consumer.consume();
    }
}
