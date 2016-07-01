package com.kumar.projects.learningdependencyinjection;

public class Person
{
	//EmailService email = new EmailService();
	MessageService messageService = null;
	
	Person(MessageService messageService)
	{
		this.messageService = messageService;
	}
	
	public void sendMessage(String subject, String message)
	{
		messageService.send(subject, message);
	}
}

/*
We all agree that this is a very simple and straightforward example that involves two simple Java classes. Nevertheless, the above has some limitations as described below.

The Person class is dependent (has a strong/tight dependency) on the Email class. There is a hard connection between these two classes. Let say we have a new and better version of email class, FastEmail, in order for us to use it, we need to go in each and every class that depends on the Email class, such as the Person class, and replace it with the new version.
Let say we parameterise the Email‘s constructor. Again we have to go in each and every class that is initialising the Email class, such as the Person class, and change it.
A design decision is taken to make the Email class Singleton (Wiki). Similar to above we need to modify all instances where it is used.
In order to improve the notifications/messages system, we decide to add different message delivery systems such as SMS or tweets. The Person class and others like it, need to all be modified in order for it to use the new implementations.
Another developer needs to use the Person class, but would like to use a different notification/message system. This cannot be achieved with the current version of the Person class as it is hardwired to the Email class. What generally happens is that the other developer duplicates the Person class and modifies it as he/she needs. The projects ends up with two versions of the Person class.
In the above points we mentioned many scenarios where code has to be changed. All changes made, need to and should be tested. How can we test the Person class without including the message delivery class such as the Email? Testing, in many cases, is left as an afterthought. The way we have the Person class constructed makes it hard to test it without involving the Email class. Furthermore, how would we automate such test? How can we use JUnit (Homepage), or the like, to automate out tests?
Moving forward in the project, the Person class starts to depend on another class that allow this object to write a letter using the Pen class for example. The Person class can use other ways to write a letter, such as Pencil class or Typewriter class, but this approach does not allow that.

These limitations can be improved by changing the way we think and refactor our code in a modular way. This is independent from dependency injection and the dependency injection framework as we will see in the following section.
*/