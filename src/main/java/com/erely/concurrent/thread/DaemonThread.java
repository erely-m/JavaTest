package com.erely.concurrent.thread;

public class DaemonThread {
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                while(true){}
            }
        };
        t.setDaemon(true);
        t.start();
        System.out.println("main thread over");

    }
}
