package com.kumar.projects.learningdependencyinjection;

public class TweetService implements MessageService
{
	public TweetService()
	{
	}
	
	public void send(String subject, String message)
	{
		System.out.println("Message sent through Tweet.");
	}
}