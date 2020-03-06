package com.erely.concurrent.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {
        ConcurrentLinkedQueue q = new ConcurrentLinkedQueue();
        q.add(1);
        q.poll();
        System.out.println(q.size());
    }
}
