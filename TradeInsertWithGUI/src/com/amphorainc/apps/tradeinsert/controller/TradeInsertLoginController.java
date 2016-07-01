package com.amphorainc.apps.tradeinsert.controller;

import java.awt.event.ActionEvent;

import com.amphorainc.apps.tradeinsert.model.TradeInsertLoginModel;
import com.amphorainc.apps.tradeinsert.view.TradeInsertLoginView;

public class TradeInsertLoginController
{
	TradeInsertLoginView tradeInsertLoginView;
	TradeInsertLoginModel tradeInsertLoginModel;
	
	public TradeInsertLoginController(){}
	
	public TradeInsertLoginController(TradeInsertLoginView tradeInsertLoginView, TradeInsertLoginModel tradeInsertLoginModel)
	{
		this.tradeInsertLoginView = tradeInsertLoginView;
		this.tradeInsertLoginModel = tradeInsertLoginModel; 
	}
	
	public void actionPerformed(ActionEvent actionEvent)
	{
	}
}