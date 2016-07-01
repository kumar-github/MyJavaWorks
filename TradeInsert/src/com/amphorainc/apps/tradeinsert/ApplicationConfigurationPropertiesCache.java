package com.amphorainc.apps.tradeinsert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class ApplicationConfigurationPropertiesCache
{
	private final Properties configProperties = new Properties();
	
	private ApplicationConfigurationPropertiesCache()
	{
	}
	
	private ApplicationConfigurationPropertiesCache(String configFile)
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
		private static final ApplicationConfigurationPropertiesCache INSTANCE = new ApplicationConfigurationPropertiesCache();
	}
	
	public static ApplicationConfigurationPropertiesCache getInstance()
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