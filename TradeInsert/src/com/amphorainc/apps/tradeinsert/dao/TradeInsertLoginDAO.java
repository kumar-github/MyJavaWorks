package com.amphorainc.apps.tradeinsert.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amphorainc.apps.tradeinsert.controller.TradeInsertLoginBean;
import com.amphorainc.apps.tradeinsert.model.TradeInsertLogin;
import com.amphorainc.apps.tradeinsert.util.ConfigUtil;
import com.amphorainc.apps.tradeinsert.util.ConfigurationCache;
import com.amphorainc.apps.tradeinsert.util.DBConnectionUtil;

public class TradeInsertLoginDAO
{
	private static final ConfigurationCache configurationCache;
	
	static
	{
		configurationCache = ConfigurationCache.getInstance();
	}
	
	public static boolean authenticateUser(String username, String password)
	{
		TradeInsertLogin tradeInsertLogin = new TradeInsertLogin(username, password);
		return authenticateUser(tradeInsertLogin);
	}
	
	public static boolean authenticateUser(String dataSourceName, String username, String password)
	{
		TradeInsertLogin tradeInsertLogin = new TradeInsertLogin(dataSourceName, username, password);
		return authenticateUser(tradeInsertLogin);
	}
	
	public static boolean authenticateUser(TradeInsertLogin tradeInsertLogin)
	{
		boolean authenticationStatus = false;
		Connection connection = null;
		
		String dataSourceName = tradeInsertLogin.getDataSourceName();
		String username = tradeInsertLogin.getUsername();
		String password = tradeInsertLogin.getPassword();
		
		try
		{
			/* try to these the DBConnectionUtil class for doing these */
			Class.forName(configurationCache.getProperty(ConfigUtil.DATABASE_DRIVER_CLASS_NAME_KEY));
			connection = DriverManager.getConnection(configurationCache.getProperty(ConfigUtil.DATABASE_CONNECTION_URL_KEY), username, password);
			
			authenticationStatus = (connection != null) ? true : false;
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException ex)
				{
					ex.printStackTrace();
				}
			}
		}
		return authenticationStatus;
	}
	
	/*private TradeInsertLogin retrieveLoginDetailsFromFile()
	{
		ObjectInputStream objectReader = null;
		try
		{
			objectReader = new ObjectInputStream(new FileInputStream(ConfigUtil.CREDENTIAL_FILE_LOCATION + "\\" + ConfigUtil.CREDENTIAL_FILE_NAME));
			return (TradeInsertLogin)objectReader.readObject();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				objectReader.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}*/
}