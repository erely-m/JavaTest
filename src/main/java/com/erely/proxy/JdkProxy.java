package com.erely.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkProxy implements InvocationHandler {

	private Object target;

	public JdkProxy() {
		super();
	}

	public JdkProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("------插入前置通知代码-------------");
		Object obj = method.invoke(target, args);
		System.out.println("------插入后置通知代码-------------");
		return obj;
	}

}
