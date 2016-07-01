package com.amphorainc.apps.tradeinsert;

import java.util.Set;

public interface ConfigurationCache1
{
	public String getProperty(String key);
	public Set<String> getAllPropertyNames();
	public boolean containsKey(String key);
}