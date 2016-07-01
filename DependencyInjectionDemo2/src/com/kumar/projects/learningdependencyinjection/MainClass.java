package com.kumar.projects.learningdependencyinjection;

public class MainClass
{
	public static void main(String[] args)
	{
		//Person p1 = new Person();
		/*this could be a problem. Here after we need arguemnt to create Person object. This is where Dependency Injection comes into picture.*/
		Person p1 = new Person(new EmailService());
		p1.sendMessage("Some Subject", "Some Message");
		Person p2 = new Person(new SMSService());
		p2.sendMessage("Some Subject", "Some Message");
		Person p3 = new Person(new TweetService());
		p3.sendMessage("Some Subject", "Some Message");
	}
}