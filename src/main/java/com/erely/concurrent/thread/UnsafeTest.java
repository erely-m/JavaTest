package com.erely.concurrent.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {

    static Unsafe unsafe; //获取Unsafe实例
    private volatile long state = 0;
    static long stateOffset;
    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            stateOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UnsafeTest test = new UnsafeTest();
        System.out.println(stateOffset);
        Boolean result = unsafe.compareAndSwapLong(test, stateOffset, 0, 1);
        System.out.println(result);
        System.out.println(unsafe.getInt(Thread.currentThread(),0));

    }
}
