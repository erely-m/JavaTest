package com.erely.concurrent.lock;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {

    private static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) {
        longAdder.increment();
        System.out.println(longAdder.sum());
        System.out.println(longAdder.sumThenReset());
    }
}
