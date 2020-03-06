package com.erely.wechat;

public class SynchronizeTest {

    public void getInstance() {
        synchronized (SynchronizeTest.class) {
            try {
                Thread.sleep(5000);
                System.out.println("get Instance end " + System.currentTimeMillis() + " thredId is" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void print() {
        System.out.println("print " + System.currentTimeMillis() + " thredId is" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        final SynchronizeTest test = new SynchronizeTest();
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                new Thread() {
                    @Override
                    public void run() {
                        System.out.println("get Instance start " + System.currentTimeMillis() + " thredId is" + Thread.currentThread().getName());
                        test.getInstance();
                        test.print();
                    }
                }.start();
            } else {
                new Thread() {
                    @Override
                    public void run() {
                        test.print();
                    }
                }.start();
            }
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
    }
}

