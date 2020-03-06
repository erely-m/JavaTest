package com.erely.concurrent.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(" Thread start");
                while (true) {
                    LockSupport.park();
                    if (!Thread.currentThread().isInterrupted()) break;
                }
                System.out.println(" Thread end");
            }
        };
        thread.start();
        System.out.println(" main thread sleep");
        Thread.sleep(1000);
        System.out.println(" main thread wake up");
        LockSupport.unpark(thread);
        thread.join();
    }
}
