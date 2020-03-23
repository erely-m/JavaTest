package com.erely.aop.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.erely.aop.annotation.After;
import com.erely.aop.annotation.Before;

/**
 * 
 * @author Administrator
 *
 */
public class AopInvocation implements InvocationHandler {
	private Object target;
	private Aspect aspect;
	
	public AopInvocation(Object target, Aspect aspect) {
		super();
		this.target = target;
		this.aspect = aspect;
	}


	public Object getTarget() {
		return target;
	}


	public void setTarget(Object target) {
		this.target = target;
	}


	public Aspect getAspect() {
		return aspect;
	}


	public void setAspect(Aspect aspect) {
		this.aspect = aspect;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Object obj;
		if(method.getName().matches(aspect.getPointCut().getMethodPath())) {
			Method[] mthds = aspect.getAdvice().getClass().getMethods();
			Method beforeMethod = null;
			Method afterMethod = null;
			for(Method mthd : mthds) {
				if(mthd.getAnnotation(Before.class) != null) {
					beforeMethod = mthd;
				}else if(mthd.getAnnotation(After.class) != null) {
					afterMethod = mthd;
				}
			}
			beforeMethod.invoke(aspect.getAdvice(), null);
			obj = method.invoke(target, args);
			afterMethod.invoke(aspect.getAdvice(), null);
		}else {
			obj =  method.invoke(target, args);
		}
		
		return obj;
	}

}
