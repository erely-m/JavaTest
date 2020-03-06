package com.erely.spi;

import java.util.ServiceLoader;

public class Test {

    public static void main(String[] args) {

        ServiceLoader<SpiDemo> spiDemos = ServiceLoader.load(SpiDemo.class);
        spiDemos.forEach(spiDemo -> spiDemo.sayHello());
    }
}
