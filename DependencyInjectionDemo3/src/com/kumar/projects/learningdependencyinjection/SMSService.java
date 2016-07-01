package com.kumar.projects.learningdependencyinjection;

public class SMSService implements MessageService
{
	public SMSService()
	{
	}
	
	public void send(String subject, String message)
	{
		System.out.println("Message sent through SMS.");
	}
}