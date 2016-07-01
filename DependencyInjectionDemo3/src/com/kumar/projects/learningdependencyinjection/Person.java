package com.kumar.projects.learningdependencyinjection;

import com.google.inject.Inject;

public class Person
{
	MessageService messageService = null;
	
	@Inject
	public Person(MessageService messageService)
	{
		this.messageService = messageService;
	}
	
	public void sendMessage(String subject, String message)
	{
		messageService.send(subject, message);
	}
}