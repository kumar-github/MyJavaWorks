package com.kumar.projects.learningdependencyinjection;

import com.google.inject.Inject;

public class Person
{
	MessageService messageService = null;
	SomeOtherService someOtherService = null;
	
	@Inject
	//public Person(MessageService messageService)
	public Person(MessageService messageService, SomeOtherService someOtherService)
	{
		this.messageService = messageService;
		this.someOtherService = someOtherService;	//not using though
	}
	
	public void sendMessage(String subject, String message)
	{
		messageService.send(subject, message);
	}
}