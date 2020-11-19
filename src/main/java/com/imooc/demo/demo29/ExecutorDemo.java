package com.imooc.demo.demo29;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new MyRunable());
        executorService.execute(new MyThread());
        executorService.shutdown();
    }
}
