package com.erely.spi;

public class MySpi implements SpiDemo {
    @Override
    public void sayHello() {
        System.out.println("Myspi Hello");
    }
}
