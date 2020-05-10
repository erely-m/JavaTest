package com.erely.spring;

import com.erely.spring.demo.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpingTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.erely.spring");
        Person p = (Person) context.getBean("person");
        p.say();
    }
}
