package com.erely.concurrent.thread;

//在共享资源wait的时候终端线程，
public class WaitNotifyInterrupt {

    public static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("线程开始");
                    //阻塞当前线程
                    synchronized (obj) {
                        System.out.println("线程挂起");
                        obj.wait();
                        System.out.println("线程继续");
                    }
                    System.out.println("线程结束");
                }catch (InterruptedException e){
                    e.printStackTrace();
                    System.out.println("线程被中断");
                }
            }
        };

        thread.start();
        Thread.sleep(1000);
        System.out.println("开始中断线程");
        thread.interrupt();
        System.out.println("中断线程结束");
        thread.join();

    }
}
