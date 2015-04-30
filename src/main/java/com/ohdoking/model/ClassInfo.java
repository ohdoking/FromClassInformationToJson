package com.ohdoking.model;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ClassInfo {

	Class<?> clazz;
	public String className;
	public String classDescription;
//	public ArrayList<MethodInfo> methods;
	public Method[] methods;
	
/*	public ClassInfo(String className){
		try {
			clazz = Class.forName(className);
			
			this.className = clazz.getCanonicalName();
			this.methods = clazz.getDeclaredMethods();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassDescription() {
		return classDescription;
	}

	public void setClassDescription(String classDescription) {
		this.classDescription = classDescription;
	}

	public Method[] getMethods() {
		return methods;
	}

	public void setMethods(Method[] methods) {
		this.methods = methods;
	}
	

	
	
}
