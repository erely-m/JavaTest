package com.erely.concurrent.thread;

public class ThreadLocalTest {

    static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    static void print(String str){
        System.out.println(str + ":" + threadLocal.get());
        threadLocal.remove();
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(){
            @Override
            public void run() {
                threadLocal.set("thread A value");
                print("Thread A");
                System.out.println("thread A remove after ：" + threadLocal.get());
            }
        };
        Thread threadB = new Thread(){
            @Override
            public void run() {
                threadLocal.set("thread B value");
                print("Thread B");
                System.out.println("thread  B remove after ：" + threadLocal.get());
            }
        };
        threadA.start();
        threadB.start();
    }
}
