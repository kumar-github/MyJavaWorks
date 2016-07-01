package com.amphorainc.apps.tradeinsert.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.amphorainc.apps.tradeinsert.controller.TradeInsertLoginController;

public class CheckboxListener implements ItemListener
{
	public CheckboxListener(){}
	
	public CheckboxListener(TradeInsertLoginController tradeInsertLoginController)
	{
	}

	@Override
	public void itemStateChanged(ItemEvent e)
	{
		System.out.println("Checked state changed...");
	}
}