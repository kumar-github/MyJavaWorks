package com.amphorainc.apps.tradeinsert.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class ConfigurationCache
{
	private final Properties configProperties = new Properties();
	private final HashMap<String, String> lookAndFeelHashMap = new HashMap<String, String>();

	private ConfigurationCache()
	{
		this("");
	}
	
	private ConfigurationCache(String configFile)
	{
		//InputStream in = this.getClass().getClassLoader().getResourceAsStream("app.config");
		//InputStream in = this.getClass().getResourceAsStream(configFile);
		try
		{
			//InputStream in = new FileInputStream("D:\\MyDropbox\\Dropbox\\My Java Workspace\\TradeInsert\\app.config");
			InputStream in = new FileInputStream("D:\\MyDropbox\\Dropbox\\My Java Workspace\\TradeInsert\\src\\com\\amphorainc\\apps\\tradeinsert\\app.config");
			//InputStream in2 = new FileInputStream(System.getProperty("user.dir") + "\\app.config");
			//File dir1 = new File(".");
			//File dir2 = new File("..");
			configProperties.load(in);
			in.close();
			
			loadAllLookAndFeel();
		}
		catch(Exception exob)
		{
			exob.printStackTrace();
		}
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
	
	public String getLookAndFeelClassByName(String lookAndFeelName)
	{
		return lookAndFeelHashMap.get(lookAndFeelName);
	}
	
	private void loadAllLookAndFeel()
	{
		loadSystemLookAndFeel();
		
		if(shouldLoadCustomLookAndFeel())
			loadCustomLookAndFeel();
	}
	
	private void loadCustomLookAndFeel()
	{
		try
		{
			InputStream in = new FileInputStream("D:\\MyDropbox\\Dropbox\\My Java Workspace\\TradeInsert\\src\\com\\amphorainc\\apps\\tradeinsert\\CustomLookAndFeel.config");
			Properties tempProperties = new Properties();
			tempProperties.load(in);
			Set<String> keys = tempProperties.stringPropertyNames();
			for(String aKey : keys)
				lookAndFeelHashMap.put(aKey, (String) tempProperties.get(aKey));
			
			in.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void loadSystemLookAndFeel()
	{
		LookAndFeelInfo[] allSystemLookAndFeel = UIManager.getInstalledLookAndFeels();
		for(LookAndFeelInfo aLookaAndFeelInfo : allSystemLookAndFeel)
		{
			if(aLookaAndFeelInfo != null)
				lookAndFeelHashMap.put(aLookaAndFeelInfo.getName(), aLookaAndFeelInfo.getClassName());
		}
	}
	
	private boolean shouldLoadCustomLookAndFeel()
	{
		if(this.getProperty("LoadCustomLookAndFeel") != null && this.getProperty("LoadCustomLookAndFeel").equalsIgnoreCase("yes"))
			return true;
		return false;
	}
}