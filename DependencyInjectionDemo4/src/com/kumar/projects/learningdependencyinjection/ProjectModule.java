package com.kumar.projects.learningdependencyinjection;

import com.google.inject.AbstractModule;

public class ProjectModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(MessageService.class).to(TweetService.class);
		bind(SomeOtherService.class).to(TestService.class);
	}
}

/*
The next natural question will be, how does the dependency injection framework knows how to initialise the MessageService? We need to tell the dependency injection framework how to create an instance of MessageService. With Guice we do that by creating a module (a class that extends AbstractModule class) as illustrated below.
*/
/*
Here we are telling the dependency injection framework (Guice) how to create an instance of the MessageService class. We also need to add an annotation to the Person class in order to allow the dependency injection framework to inject the necessary parameters.
*/