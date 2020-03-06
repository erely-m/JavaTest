package com.erely.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {

    private  static BlockingQueue queue = new SynchronousQueue(); //单个元素队列

    public static void main(String[] args) {
        new Thread(new ConsumerThread()).start();
        new Thread(new ProviderThread()).start();
    }

    static class ProviderThread implements Runnable{

        public void run() {
            System.out.println(queue.offer(1)); //存入元素如果有消费方等待消费则插入成功
            System.out.println(queue.offer(1)); //存入元素如果有消费方等待消费则插入成功
            System.out.println(queue.offer(1)); //存入元素如果有消费方等待消费则插入成功
            try {
                queue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ConsumerThread implements Runnable{

        public void run() {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
