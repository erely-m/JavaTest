package com.erely.ribbon;

public class C<T extends D> extends B<T>{
    public T t;
    public C(){}
    public static void main(String[] args) {
        new C();
    }
}
