package com.erely.aop.impl;
/**
 * AOP �����ӵ�
 * @author Administrator
 *
 */
public class PointCut {
	
	private String classPath;  //��·��ƥ��
	private String methodPath; //����ƥ��
	
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
