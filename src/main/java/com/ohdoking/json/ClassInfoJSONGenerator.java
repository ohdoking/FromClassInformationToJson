package com.ohdoking.json;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.ohdoking.model.ClassInfo;

public class ClassInfoJSONGenerator {

	public static void main(String[] args) throws IOException {

		// String className = "com.ohdoking.json.CloudApplicationURL";
		String packageName = "org.cloudfoundry.ide.eclipse.server.core";

		Reflections reflections = new Reflections(packageName,
				new SubTypesScanner(false));

		Set<Class<? extends Object>> allClasses = reflections
				.getSubTypesOf(Object.class);

		System.out.println(allClasses);

		System.out.println("good");

		Iterator<Class<? extends Object>> itr = allClasses.iterator();
		
		OutputStream fos = new FileOutputStream("ClassInfoJson.txt");
		JsonGenerator jsonGenerator = Json.createGenerator(fos);
		jsonGenerator.writeStartArray();
		while (itr.hasNext()) {

			Class<? extends Object> s = itr.next();
			if(!s.isAnonymousClass()){
				
				System.out.println(s.getName());
				ClassInfoJSONGenerator.generateJsonFile(s.getName(),jsonGenerator);
			}
		}
		jsonGenerator.writeEnd();
		jsonGenerator.close();

		System.out.println("finish");

	}

	public static void generateJsonFile(String className,JsonGenerator jsonGenerator)
			throws FileNotFoundException {

		int i = 1;

		ClassInfo ci = ClassInfoJSONWriter.createClassInfo(className);

		/*
		 * Json Create
		 */

		jsonGenerator.writeStartObject(); // {

		System.out.println("클래스이름 : " + ci.className);
		jsonGenerator.write("className", ci.getClassName());
		jsonGenerator.write("description", "");

		/*
		 * method Array
		 */
		jsonGenerator.writeStartArray("methods"); // start of phone num array
		for (Method method : ci.methods) {

			jsonGenerator.writeStartObject(); // {

			jsonGenerator.write("name", method.getName());
			jsonGenerator.write("returnType", method.getReturnType()
					.getSimpleName());

			/*
			 * parmas Array
			 */
			jsonGenerator.writeStartArray("params"); // start of phone num array
			Parameter[] params = method.getParameters();
			for (Parameter param : params) {
				jsonGenerator.writeStartObject(); // {
				jsonGenerator.write("type", param.getType().getSimpleName());
				jsonGenerator.write("name", param.getName());
				jsonGenerator.writeEnd(); // params writeStartObject End
			}
			jsonGenerator.writeEnd(); // parmas Array End

			jsonGenerator.write("description", "");
			jsonGenerator.writeEnd(); // method writeStartObject End

			i++;
		}
		jsonGenerator.writeEnd(); // method Array End
		jsonGenerator.writeEnd(); // end of phone num array

		

	}

}
