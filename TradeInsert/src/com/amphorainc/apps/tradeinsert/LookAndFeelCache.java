package com.amphorainc.apps.tradeinsert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class LookAndFeelCache
{
	private final Properties configProperties = new Properties();

	private LookAndFeelCache()
	{
	}

	private LookAndFeelCache(String configFile)
	{
		//InputStream in = this.getClass().getClassLoader().getResourceAsStream("app.config");
		InputStream in = this.getClass().getResourceAsStream(configFile);
		try
		{
			//InputStream in = new FileInputStream("D:\\MyDropbox\\Dropbox\\My Java Workspace\\TradeInsert\\app.config");
			//InputStream in2 = new FileInputStream(System.getProperty("user.dir") + "\\app.config");
			//File dir1 = new File(".");
			//File dir2 = new File("..");
			configProperties.load(in);
			in.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}

	private static class LazyHolder
	{
		private static final LookAndFeelCache INSTANCE = new LookAndFeelCache();
	}

	public static LookAndFeelCache getInstance()
	{
		return LazyHolder.INSTANCE;
	}

	public String getProperty(String key)
	{
		return configProperties.getProperty(key);
	}

	public Set<String> getAllPropertyNames()
	{
		return configProperties.stringPropertyNames();
	}

	public boolean containsKey(String key)
	{
		return configProperties.containsKey(key);
	}
}