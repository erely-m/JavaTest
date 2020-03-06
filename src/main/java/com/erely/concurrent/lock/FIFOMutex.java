package com.erely.concurrent.lock;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * 先进先出锁
 */
public class FIFOMutex {
    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiter = new ConcurrentLinkedQueue<>();
    public void lock() {
        Thread currThread = Thread.currentThread(); //获取当前线程
        waiter.add(currThread); //当前线程添加进队列中
        Boolean isInterrupted = false;//中断标志

        while(waiter.peek() != currThread || !locked.compareAndSet(false,true)){
            LockSupport.park();
            if(currThread.isInterrupted()){
                isInterrupted = true;
            }
        }
        waiter.remove();
        if(isInterrupted){
            Thread.interrupted();
        }
    }

    public void unLock() {
        locked.set(false);
        LockSupport.unpark(waiter.peek());
    }


    public static void main(String[] args) throws InterruptedException {
        FIFOMutex lock = new FIFOMutex();

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                System.out.println("Thread1 try get lock");
                lock.lock();
                System.out.println("Thread1 get lock success!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unLock();
                System.out.println("Thread1 unlock success");
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                System.out.println("Thread2 try get lock");
                lock.lock();
                System.out.println("Thread2 get lock success!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unLock();
                System.out.println("Thread2 unlock success");
            }
        };

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
