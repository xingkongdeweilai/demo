package com.imooc.demo.demo29;

public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println("MyThread开冲" + i);
        }
    }
}
