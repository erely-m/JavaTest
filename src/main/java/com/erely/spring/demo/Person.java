package com.erely.spring.demo;

import org.springframework.stereotype.Component;

@Component
public class Person {

    public Person(){
        System.out.println( "this is person!");
    }

    public void say(){

        System.out.println("this is say method.");
    }

}
