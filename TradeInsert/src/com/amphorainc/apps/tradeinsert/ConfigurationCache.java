package com.amphorainc.apps.tradeinsert;

import java.util.Properties;
import java.util.Set;

public class ConfigurationCache
{
	private final Properties configProperties = new Properties();

	private ConfigurationCache()
	{
		this("");
	}
	
	private ConfigurationCache(String s)
	{
	}
	
	private static class LazyHolder
	{
		private static final ConfigurationCache SINGLETON_INSTANCE = new ConfigurationCache();
	}
	
	public static ConfigurationCache getInstance()
	{
		return LazyHolder.SINGLETON_INSTANCE;
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