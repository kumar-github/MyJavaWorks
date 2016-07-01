package com.kumar.projects.learningdependencyinjection;

public class EmailService implements MessageService
{
	public EmailService()
	{
	}
	
	public void send(String subject, String message)
	{
		System.out.println("Message sent through Email.");
	}
}