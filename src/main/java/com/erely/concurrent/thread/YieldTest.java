package com.erely.concurrent.thread;

public class YieldTest implements Runnable {

    public YieldTest() {
        Thread t = new Thread(this);
        t.setPriority((int) (Math.round(1.0)*10));
        t.start();
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            if (i % 5 == 0) {
                System.out.println(Thread.currentThread() + "yield cpu");
                Thread.yield();//让出当前线程CPU使用权
            }
//            System.out.println(Thread.currentThread() + ": i:" + i);
        }
        System.out.println(Thread.currentThread() + "is over");
    }

    public static void main(String[] args) {
        new YieldTest();
        new YieldTest();
        new YieldTest();
    }
}
