package com.amphorainc.apps.tradeinsert.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConfigurationCache
{
	private final HashMap<String, String> configProperties = new HashMap<String, String>();

	private ConfigurationCache()
	{
		this("");
	}

	private ConfigurationCache(String configFile)
	{
		/*
		//InputStream in = this.getClass().getClassLoader().getResourceAsStream("app.config");
		//InputStream in = this.getClass().getResourceAsStream(configFile);
		try
		{
			InputStream in = new FileInputStream("D:\\MyDropbox\\Dropbox\\My Java Workspace\\TradeInsert\\config\\app.xml");
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
		 */
		loadConfigurationFromXMLFile();
		//loadLookAndFeel();
	}

	private static class LazyHolder
	{
		private static final ConfigurationCache SINGLETON_INSTANCE = new ConfigurationCache();
	}

	public static ConfigurationCache getInstance()
	{
		return LazyHolder.SINGLETON_INSTANCE;
	}
	
	private void loadConfigurationFromXMLFile()
	{
		try
		{
			InputStream in = new FileInputStream("D:\\MyDropbox\\Dropbox\\My Java Workspace\\TradeInsert\\config\\app.xml");
			//InputStream in2 = new FileInputStream(System.getProperty("user.dir") + "\\app.config");
			//File dir1 = new File(".");
			//File dir2 = new File("..");

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document xmlDocument = documentBuilder.parse(in);

			xmlDocument.getDocumentElement().normalize();

			Element rootElement = xmlDocument.getDocumentElement();
			//System.out.print(root.getElementsByTagName("LoadCustomLookAndFeel").item(0).getTextContent());

			NodeList nodeList = rootElement.getElementsByTagName("*");
			for(int i=0;i<nodeList.getLength();i++)
			{
				if(!nodeList.item(i).hasAttributes())
				{
					configProperties.put(nodeList.item(i).getNodeName(), nodeList.item(i).getTextContent());
				}
				/*NodeList nList = doc.getElementsByTagName("dbConnection");
				Node node = nList.item(0);
				//System.out.println("\nCurrent Element :" + node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element)node;
					System.out.println("First Name : " + eElement.getElementsByTagName("databaseDriverClassName").item(0).getTextContent());
				}*/
			}
			in.close();
			
			//loads all the system defined LookAndFeel into the hash map
			loadLookAndFeel();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void loadLookAndFeel()
	{
		loadSystemLookAndFeel();
	}

	private void loadSystemLookAndFeel()
	{
		for(LookAndFeelInfo aLookaAndFeelInfo : UIManager.getInstalledLookAndFeels())
		{
			if(aLookaAndFeelInfo != null)
				configProperties.put(aLookaAndFeelInfo.getName(), aLookaAndFeelInfo.getClassName());
		}
	}

	public String getProperty(String key)
	{
		return configProperties.get(key);
	}

	public Set<String> getAllPropertyNames()
	{
		return configProperties.keySet();
	}

	public boolean containsKey(String key)
	{
		return configProperties.containsKey(key);
	}

	public String getLookAndFeelClassByName(String lookAndFeelName)
	{
		return configProperties.get(lookAndFeelName);
	}
	
	/*
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
			InputStream in = new FileInputStream("D:\\MyDropbox\\Dropbox\\My Java Workspace\\TradeInsert\\config\\app.xml");
			Properties tempProperties = new Properties();
			tempProperties.load(in);
			Set<String> keys = tempProperties.stringPropertyNames();
			for(String aKey : keys)
				//lookAndFeelHashMap.put(aKey, (String) tempProperties.get(aKey));
				configProperties.put(aKey, (String) tempProperties.get(aKey));

			in.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private boolean shouldLoadCustomLookAndFeel()
	{
		if(this.getProperty("LoadCustomLookAndFeel") != null && this.getProperty("LoadCustomLookAndFeel").equalsIgnoreCase("yes"))
			return true;
		return false;
	}
	*/
}