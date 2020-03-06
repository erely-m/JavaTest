package com.erely.concurrent.thread;

public class Wait4queue {

    public static volatile Object objA = new Object();
    public static volatile Object objB = new Object();

    static class ThreadA extends Thread {
        @Override
        public void run() {
            System.out.println("线程A 尝试获取A资源");
            synchronized (objA) {
                System.out.println("线程A 获取A资源成功");
                System.out.println("线程A 尝试获取B资源");
                synchronized (objB) { //在同步代码块中释放上一个资源线程阻塞在当前位置，B资源没有释放
                    System.out.println("线程A 获取B资源成功");
                    System.out.println("线程A 挂起线程，释放A资源");
                    try {
                        objA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程B 尝试获取A资源");
            synchronized (objA) {
                System.out.println("线程B 获取A资源成功");
                System.out.println("线程B 尝试获取B资源");
                synchronized (objB) {
                    System.out.println("线程B 获取B资源成功");
                    System.out.println("线程B 挂起线程，释放A资源");
                    try {
                        objA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread a = new ThreadA();
        Thread b = new ThreadB();

        a.start();
        b.start();

        a.join();
        b.join();
        System.out.println("全部处理完成");
    }
}
