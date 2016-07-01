package com.kumar.projects.learningdependencyinjection;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class MainClass
{
	public static void main(String[] args)
	{
		Injector injector = Guice.createInjector(new ProjectModule());
		Person p1 = injector.getInstance(Person.class);
		p1.sendMessage("Some Subject", "Some Message");
	}
}