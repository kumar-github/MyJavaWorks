package com.amphorainc.apps.tradeinsert.main;

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
		try
		{
			new GUIRunner().run();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}