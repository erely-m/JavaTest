package com.erely.concurrent.queue;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Consumer;

public class ArrayBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue abq = new ArrayBlockingQueue(10);

        abq.offer(111);
        abq.offer("dfsa");
        abq.offer("fdsaf");
        abq.offer("fdsaf");
        abq.offer("fdsaf");
        abq.offer("fdsaf");

        Iterator t = abq.iterator();
        t.forEachRemaining(new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println(o);
            }
        });
        while(t.hasNext()){
            System.out.println(t.next());
        }

    }
}
