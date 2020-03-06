package com.erely.wechat;

public class VolatileTest {
    private int a = 0;

    public synchronized void increase() {
        a++;
    }
    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 100000; j++) {
                        test.increase();
                    }
                }
            }.start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(test.a);
    }
}
