package com.imooc.demo;

public class Son extends Father {

    public void doSomething() {
        print();
    }

    public static void main(String[] args) {
        Son son = new Son();
        son.doSomething();
    }
}
