package com.kumar.projects.learningdependencyinjection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class MainClass {

	public static void main(String[] args) throws Exception
	{
		Object rightSide = null;
		Class<?> presenterClass = Class.forName("com.kumar.projects.learningdependencyinjection.Presenter");
		Object leftSide = presenterClass.newInstance(); //presenter
		Field[] allFields = presenterClass.getDeclaredFields();
		for(Field aField : allFields)
		{
			Annotation annotation = aField.getAnnotation(PleaseInject.class);
			if(annotation != null)
			{
				aField.setAccessible(true);
				Class<?> fieldType = aField.getType();
				rightSide = fieldType.newInstance();
				//aField.set(leftSide, rightSide);
			}
		}
		System.out.println("left side : " + leftSide);
		System.out.println("right side : " + rightSide);
	}
}