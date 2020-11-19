package com.imooc.demo.demo29;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCooperation {

    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new DepositTask());
        executorService.execute(new WithdrawTask());
        executorService.shutdown();
        System.out.println("-->任务结束");
    }

    public static class DepositTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                account.deposit(ThreadLocalRandom.current().nextInt(1, 100));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class WithdrawTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                account.withdraw(ThreadLocalRandom.current().nextInt(1, 100));
            }
        }
    }

    private static class Account {
        private static Lock lock = new ReentrantLock();
        private static Condition condition = lock.newCondition();

        private int balance;

        public int getBalance() {
            return balance;
        }

        /**
         * 取钱
         * @param amount
         */
        public void withdraw(int amount) {
            lock.lock();
            try {
                while (balance < amount) {
                    System.out.println("余额不足，余额：" + balance + "，需要：" + amount);
                    condition.await();
                }
                balance -= amount;
                System.out.println("取出：" + amount + "，剩余：" + balance);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void deposit(int amount) {
            lock.lock();
            try {
                balance += amount;
                System.out.println("存入：" + amount + "，剩余：" + balance);
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

}
