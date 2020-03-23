package com.erely.aop.impl;
/**
 * AOP 中连接点
 * @author Administrator
 *
 */
public class PointCut {
	
	private String classPath;  //类路径匹配
	private String methodPath; //方法匹配
	
	public PointCut(String classPath, String methodPath) {
		super();
		this.classPath = classPath;
		this.methodPath = methodPath;
	}
	public String getClassPath() {
		return classPath;
	}
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	public String getMethodPath() {
		return methodPath;
	}
	public void setMethodPath(String methodPath) {
		this.methodPath = methodPath;
	}
	
	

}
