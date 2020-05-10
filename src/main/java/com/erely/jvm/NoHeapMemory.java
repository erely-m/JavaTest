package com.erely.jvm;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

public class NoHeapMemory {

    public static void clean(final ByteBuffer byteBuffer) {
        if (byteBuffer.isDirect()) {
            ((DirectBuffer) byteBuffer).cleaner().clean();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 200);
        System.out.println("start");
        Thread.sleep(15 * 1000);
        clean(byteBuffer);
        System.out.println("end");
        Thread.sleep(15 * 1000);

    }
}
