package com.erely.jvm;

public class ClassTest {

    private String name;
    private int value;

    public void f2(){
        f1();
    }
    public  void f1(){
        name = "test";
        value = 123;
    }

}
