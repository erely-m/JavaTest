package com.erely.concurrent.lock;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

public class LongAccumulatorTest {
    private static volatile LongAccumulator accumulator = new LongAccumulator(new LongBinaryOperator() {
        @Override
        public long applyAsLong(long left, long right) {
            return left + right;
        }
    }, 0);
    public static void main(String[] args) {
        accumulator.accumulate(1);
        System.out.println(accumulator.get());
    }
}
