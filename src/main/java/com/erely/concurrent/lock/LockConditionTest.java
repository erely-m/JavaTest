package com.erely.concurrent.lock;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionTest {
    public static void main(String[] args) throws InterruptedException {
        MyLock lock = new MyLock();
        Condition consumerListener = lock.newCondition();
        Condition providerListener = lock.newCondition();
        PriorityQueue queue = new PriorityQueue<String>();

        Thread consumerThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                        lock.lock();
                        if (queue.size() == 0) {
                            System.out.println("队列空");
                            consumerListener.await();
                        }
                        String str = (String) queue.poll();
                        System.out.println(str);
                        providerListener.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };

        Thread providerThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        if (queue.size() == 5) {
                            System.out.println("队列满");
                            providerListener.await();
                        }
                        queue.offer("fdsaf" + Math.random());
                        consumerListener.signal();
                        System.out.println("插入元素成功");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };
        consumerThread.start();
        providerThread.start();
        consumerThread.join();
        providerThread.join();
    }
}
