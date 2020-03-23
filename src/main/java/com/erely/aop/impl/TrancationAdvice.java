package com.erely.aop.impl;

import com.erely.aop.Advice;
import com.erely.aop.annotation.After;
import com.erely.aop.annotation.Before;

/**
 * 事务增强接口
 * @author Administrator
 *
 */
public class TrancationAdvice implements Advice{
	@Before
	public void startTrancation() {
		System.out.println("开始事务");
	}

	@After
	public void endTrancation() {
		System.out.println("结束事务");
	}
	
}
