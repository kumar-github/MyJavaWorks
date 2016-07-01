package com.amphorainc.apps.tradeinsert.main;

import java.io.FileInputStream;
import java.util.Properties;

public class TradeInsertMainClass
{
	public static void main(String[] args) throws Exception
	{
		/*SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new GUIRunner().run();
			}
		});*/
		new GUIRunner().launchApplication();
	}
}