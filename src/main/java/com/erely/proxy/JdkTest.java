package com.erely.proxy;

import java.lang.reflect.Proxy;

public class JdkTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		JdkStudent student = new JdkStudent();
        Person p = (Person) Proxy.newProxyInstance(student.getClass().getClassLoader(),
                student.getClass().getInterfaces(), new JdkProxy(student));
        System.out.println(p);
        p.say();

	}

}
