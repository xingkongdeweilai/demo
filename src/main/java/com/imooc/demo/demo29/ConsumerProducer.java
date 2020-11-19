package com.imooc.demo.demo29;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducer {

    private static Buffer buffer = new Buffer();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WriteTask());
        executorService.execute(new ReadTask());
        executorService.shutdown();
    }

    private static class WriteTask implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                buffer.write(i++);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ReadTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                buffer.read();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Buffer {
        private static final int CAPACITY = 1;
        private LinkedList<Integer> queue = new LinkedList<>();
        private static Lock lock = new ReentrantLock();
        private Condition notFull = lock.newCondition();
        private Condition notEmpty = lock.newCondition();

        public void write(int value) {
            lock.lock();
            try {
                while (queue.size() == CAPACITY) {
                    System.out.println(Thread.currentThread().getName() + "-->队列已满，等待notFull状态");
                    notFull.await();
                    System.out.println(Thread.currentThread().getName() + "-->notFull被唤醒");
                }
                queue.offer(value);
                System.out.println(Thread.currentThread().getName() + "-->写入：" + value);
                notEmpty.signal();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public int read() {
            lock.lock();
            int value = 0;
            try {
                while (queue.isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + "-->队列已空，等待notEmpty状态");
                    notEmpty.await();
                    System.out.println(Thread.currentThread().getName() + "-->notEmpty被唤醒");
                }
                value = queue.remove();
                System.out.println(Thread.currentThread().getName() + "-->读出：" + value);
                notFull.signal();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
                return value;
            }
        }
    }

}
