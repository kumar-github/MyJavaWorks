package com.amphorainc.apps.tradeinsert.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil
{
	private static final ConfigurationCache configurationCache;
	
	static
	{
		configurationCache = ConfigurationCache.getInstance();
	}
	
	public DBConnectionUtil()
	{
	}
	
	public static Connection createConnection()
	{
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/ravi";
		String username = "root";
		String password = "root";

		try
		{
			try
			{
				Class.forName("net.sourceforge.jtds.jdbc.Driver");
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, username, password);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}

	/*we will come here for windows authentication*/
	public Connection connectToDB(String url)
	{
		Connection aDBConnection = null;
		try
		{
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			aDBConnection = DriverManager.getConnection(url);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return aDBConnection;
	}

	/*we will come here for sql authentication*/
	public Connection connectToDB(String url, String username, String password)
	{
		Connection aDBConnection = null;
		try
		{
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			aDBConnection = DriverManager.getConnection(url, username, password);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return aDBConnection;
	}

	public static boolean authenticate(String username, String password)
	{
		boolean authenticationStatus = false;
		Connection connection = null;

		try
		{
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

	public static boolean authenticateUser(String connectionUrl, String username, String password)
	{
		return false;
	}
}