package com.erely.aop.impl;
/**
 * IOC ÈÝÆ÷Ä£Äâ
 * @author Administrator
 *
 */

import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;

import com.erely.proxy.Person;

public class IOCContainer {

	private ConcurrentHashMap<String, Object> beans = new ConcurrentHashMap<>();
	
	private Aspect aspect;
	
	public IOCContainer() throws Exception {
		
		init();
	}
	
	public void init() throws Exception { //³õÊ¼»¯bean
		
		aspect = new Aspect(new PointCut("com.erely.proxy.JdkStudent", "say*"), new TrancationAdvice());
		
		Object obj = Class.forName("com.erely.proxy.JdkStudent").newInstance();
		
		if(aspect != null && aspect.getPointCut().getClassPath().matches(obj.getClass().getName())) {
			
			obj = Proxy.newProxyInstance(this.getClass().getClassLoader(), obj.getClass().getInterfaces(), new AopInvocation(obj, aspect));
		}
		
		beans.put("student", obj);
	}
	
	public Object getBeans(String name ) {
		
		return beans.get(name);
	}
	
	public static void main(String[] args) throws Exception {
		
		IOCContainer ioc = new IOCContainer();
		Person p =(Person) ioc.getBeans("student");
		p.say();
	}
}
