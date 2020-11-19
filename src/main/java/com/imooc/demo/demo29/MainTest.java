package com.imooc.demo.demo29;

public class MainTest {
    public static void main(String[] args) {
        Thread thread1 = new MyThread();
        thread1.start();

        Runnable runnable = new MyRunable();
        Thread thread2 = new Thread(runnable);
        thread2.start();
    }
}
