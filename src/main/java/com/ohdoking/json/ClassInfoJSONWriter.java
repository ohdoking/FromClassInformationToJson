package com.ohdoking.json;

import com.ohdoking.model.ClassInfo;

public class ClassInfoJSONWriter {

	public static ClassInfo createClassInfo(String className) {

		ClassInfo ci = new ClassInfo();
		
		try {
			Class<?> clazz = Class.forName(className);
			
			ci.setClassName(clazz.getSimpleName());
			ci.setMethods(clazz.getDeclaredMethods());
		} catch (ClassNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return ci;
	}

}
