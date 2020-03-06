package com.erely.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

    private static CyclicBarrier cb = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println("所有线程到达屏障点");
        }
    });

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(new Runnable() {
            @Override
            public void run() {
                int count = 10;
                while (count-- > 0) {
                    try {
                        System.out.println("线程1 执行前置任务");
                        cb.await();
                        System.out.println("线程1 执行后置任务");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        es.submit(new  Runnable() {
                              @Override
                              public void run() {
                                  int count = 10;
                                  while (count-- > 0) {
                                      try {
                                          System.out.println("线程2 执行前置任务");
                                          cb.await();
                                          System.out.println("线程2 执行后置任务");
                                      } catch (InterruptedException e) {
                                          e.printStackTrace();
                                      } catch (BrokenBarrierException e) {
                                          e.printStackTrace();
                                      }
                                  }
                              }
                          });

        es.shutdown();
    }
}
