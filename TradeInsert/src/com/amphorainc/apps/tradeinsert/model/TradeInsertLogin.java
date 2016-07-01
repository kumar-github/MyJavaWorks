package com.amphorainc.apps.tradeinsert.model;

import java.io.Serializable;

public class TradeInsertLogin implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String dataSourceName;
	private String username;
	private String password;
	
	public TradeInsertLogin()
	{
	}
	
	public TradeInsertLogin(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public TradeInsertLogin(String dataSourceName, String username, String password)
	{
		this.dataSourceName = dataSourceName;
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the dataSourceName
	 */
	public String getDataSourceName()
	{
		return dataSourceName;
	}

	/**
	 * @param dataSourceName the dataSourceName to set
	 */
	public void setDataSourceName(String dataSourceName)
	{
		this.dataSourceName = dataSourceName;
	}

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
}