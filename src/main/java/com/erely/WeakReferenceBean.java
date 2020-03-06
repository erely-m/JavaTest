package com.erely;

public class WeakReferenceBean {

    String name;

    public WeakReferenceBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("对象被回收");
    }

    @Override
    public String toString() {
        return this.name;
    }
}
