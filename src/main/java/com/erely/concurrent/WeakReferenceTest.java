package com.erely.concurrent;

import com.erely.WeakReferenceBean;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {

    public static void main(String[] args) throws InterruptedException {

        WeakReference<WeakReferenceBean> wr = new WeakReference(new WeakReferenceBean("test"));
        System.out.println(wr.get());
//        WeakReferenceBean wrb = wr.get();
        System.gc();

        Thread.sleep(5000);
    }

}
