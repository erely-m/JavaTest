package com.erely.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchTest {

    private static CountDownLatch cdl = new CountDownLatch(1);
    private static CountDownLatch cdl1 = new CountDownLatch(10);
    private static AtomicInteger ai = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread() + " await   time:" + System.nanoTime());
                        cdl.await();
                        System.out.println(Thread.currentThread() + " run     time:" + System.nanoTime());
                        ai.incrementAndGet();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        cdl1.countDown();
                    }
                }
            });
        }
        cdl.countDown();
        cdl1.await();
        System.out.println(ai.get());
        service.shutdownNow();
    }
}
