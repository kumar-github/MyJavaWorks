package org.apache.poi.hssf.usermodel.examples;

class Student
{
	int sno;
	String sname;
	
	void setStudentDetails(int sno, String sname)
	{
		this.sno = sno;
		this.sname = sname;
	}
	
	void showStudentDetails()
	{
		System.out.println("sno : " + sno);
		System.out.println("sname : " + sname);
	}
}

public class MainClass
{
	public static void main(String[] args)
	{
		Student[] st = new Student[5];
		
		for(int i=0;i<5;i++)
		{
			st[i] = new Student();
			st[i].setStudentDetails(1231, "Abcd");
		}
		
		for(int i=0;i<5;i++)
		{
			st[i].showStudentDetails();
		}
	}
}