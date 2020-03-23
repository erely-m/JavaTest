package com.erely.aop.impl;

import com.erely.aop.Advice;

/**
 * ÇÐÃæ
 * @author Administrator
 *
 */
public class Aspect {
	
	private PointCut pointCut;
	private Advice advice;
	
	public Aspect(PointCut pointCut, Advice advice) {
		super();
		this.pointCut = pointCut;
		this.advice = advice;
	}
	public PointCut getPointCut() {
		return pointCut;
	}
	public void setPointCut(PointCut pointCut) {
		this.pointCut = pointCut;
	}
	public Advice getAdvice() {
		return advice;
	}
	public void setAdvice(Advice advice) {
		this.advice = advice;
	}
}
