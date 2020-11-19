package com.imooc.demo.demo29;

public class MyRunable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println("MyRunable开冲" + i);
        }
    }
}
