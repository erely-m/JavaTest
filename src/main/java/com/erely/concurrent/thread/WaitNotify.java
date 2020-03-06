package com.erely.concurrent.thread;

public class WaitNotify {

    public static volatile Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread() {
            @Override
            public void run() {

                synchronized (obj) {
                    System.out.println("thread A start wait");
                    try {
                        obj.wait();
                        System.out.println("thread A end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread threadB = new Thread() {
            @Override
            public void run() {

                synchronized (obj) {
                    System.out.println("thread B start wait");
                    try {
                        obj.wait();
                        System.out.println("thread B end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        //notify方法
        Thread threadC = new Thread() {
            @Override
            public void run() {

                synchronized (obj) {
                    System.out.println("thread C notify");
                    obj.notify();
                }
            }
        };

        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadC.start();
        threadA.join();
        threadB.join();
        threadC.join();
    }
}
