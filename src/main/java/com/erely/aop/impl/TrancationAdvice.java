package com.erely.aop.impl;

import com.erely.aop.Advice;
import com.erely.aop.annotation.After;
import com.erely.aop.annotation.Before;

/**
 * ������ǿ�ӿ�
 * @author Administrator
 *
 */
public class TrancationAdvice implements Advice{
	@Before
	public void startTrancation() {
		System.out.println("��ʼ����");
	}

	@After
	public void endTrancation() {
		System.out.println("��������");
	}
	
}
