package com.erely.fastjson;


public class Bean2Json {

    static class Test {
        String name;
        String value;
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.name = "111";
        t.value = "434";

        System.out.println();
    }
}

