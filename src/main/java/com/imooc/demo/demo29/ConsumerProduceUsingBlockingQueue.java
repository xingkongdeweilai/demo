package com.imooc.demo.demo29;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 阻塞队列
 */
public class ConsumerProduceUsingBlockingQueue {

    private static ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(2);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new ProducerTask());
        executorService.execute(new ConsumerTask());
    }

    private static class ProducerTask implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                System.out.println(Thread.currentThread().getName() + "-->准备写入" + i);
                try {
                    buffer.put(i++);
                    System.out.println(Thread.currentThread().getName() + "-->写入后" + buffer);
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ConsumerTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    System.err.println(Thread.currentThread().getName() + "-->读出" + buffer.take());
                    System.err.println(Thread.currentThread().getName() + "-->读出后" + buffer);
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
