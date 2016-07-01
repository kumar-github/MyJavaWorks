package com.kumar.projects.learningdependencyinjection;

public class Presenter
{
	@PleaseInject
	private Boundary boundary;
	
	@Override
	public String toString()
	{
		return "Presenter Object";
	}
}