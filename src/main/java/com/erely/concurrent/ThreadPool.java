package com.erely.concurrent;

import java.lang.reflect.Field;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    private boolean anInt;

    public static void main(String[] args) throws NoSuchFieldException {

        ThreadPoolExecutor threadPoolExecutor;
        threadPoolExecutor = new ThreadPoolExecutor(10,10, 60,TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
//        Field f =ThreadPool.class.getDeclaredField("anInt");
//        Class type = f.getType();
//        System.out.println(f.getType());
//        System.out.println(f.getGenericType());
//        System.out.println(f.getModifiers());
    }
}
