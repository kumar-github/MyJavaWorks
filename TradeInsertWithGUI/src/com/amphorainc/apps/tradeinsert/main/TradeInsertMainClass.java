package com.amphorainc.apps.tradeinsert.main;

import javax.swing.SwingUtilities;

public class TradeInsertMainClass
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new GUIRunner().run();
			}
		});
	}
}