package com.erely.concurrent;

import java.util.concurrent.atomic.AtomicLong;

/*
 上篇文章以AtomicInteger为切入点，和大家一起探讨了JUC中atomic的原子操作实现。上篇文章末尾也给大家提出了一个疑问
 那就是由于原子类每次更新只能有一个线程去CAS操作成功，在高并发情况下，这种操作也会成为系统的一个限制。
 接下来的文章笔者将和读者一起探讨LongAdder如何解决Atomic多线程下的瓶颈。
 首先我们分析Atomic瓶颈的关键点，是由于多线程操作的是同一个共享变量。
 为了解决这个问题LongAdder将值分为一个base和多个cell。每个线程可以操作对base或者每个cell进行操作。
 最后的结果值是base+所有cell的值。
 下面我们从源码进行探讨功能的实现
 首先看一下类的继承结构
 继承了Striped64类，该类是实现多线程访问不同变量的关键类。接下来笔者便从这个类作为切入点探讨
 **/
public class AtomicTest {

    private static AtomicLong aLong = new AtomicLong();
    private static Integer[] array1 = new Integer[]{0, 2, 6, 8, 0, 4, 6, 0};
    private static Integer[] array2 = new Integer[]{0, 3, 6, 1, 0, 4, 6, 9};

    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread() {
//            @Override
//            public void run() {
//                for (int i = 0; i < array1.length; i++){
//                    if(array1[i] ==0){
//                        aLong.incrementAndGet();
//                    }
//                }
//            }
//        };
//
//        Thread t2 = new Thread() {
//            @Override
//            public void run() {
//                for (int i = 0; i < array2.length; i++){
//                    if(array2[i] ==0){
//                        aLong.incrementAndGet();
//                    }
//                }
//            }
//        };
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();
//        System.out.println(aLong.get());
        Thread threadA = new Thread() {
            @Override
            public void run() {
                Long value = aLong.get();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(aLong.compareAndSet(value, 2l));
            }
        };
        Thread threadB = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Long value = aLong.get();
                System.out.println(value);
                aLong.compareAndSet(value, 1L);
                aLong.compareAndSet(value, 0L);
            }
        };

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }

}
