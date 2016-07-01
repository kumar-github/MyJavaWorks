package com.kumar.projects.learningnetworking;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;

public class ServerDemo
{
	public static void main(String[] args) throws Exception
	{
		int i = 0;
		ServerSocket serverSocket = new ServerSocket(8080);
		
		System.out.println("Server is listening for requests...");
		while(true)
		{
			final Socket client = serverSocket.accept();
			InputStream inputStream = client.getInputStream();
			byte[] b = new byte[1000];
			inputStream.read(b);
			System.out.println("client " + ++i + " --> " + client);
			System.out.println(new String(b));
			
			client.getOutputStream().write(new String("<html><body bgcolor=orange></body></html>").getBytes("UTF-8"));
			client.getOutputStream().write(new String("welcome").getBytes("UTF-8"));
			client.getOutputStream().flush();
			
		}
		//System.out.println("Server shutdown...");
	}
}