package com.amphorainc.apps.tradeinsert.controller;

import com.amphorainc.apps.tradeinsert.model.TradeInsertMainModel;
import com.amphorainc.apps.tradeinsert.view.TradeInsertMainView;

public class TradeInsertMainController
{
	private TradeInsertMainView tradeInsertMainView;
	private TradeInsertMainModel tradeInsertMainModel;
	
	public TradeInsertMainController(){}
	
	public TradeInsertMainController(TradeInsertMainView tradeInsertMainView, TradeInsertMainModel tradeInsertMainModel)
	{
		this.tradeInsertMainView = tradeInsertMainView;
		this.tradeInsertMainModel = tradeInsertMainModel;
		
		addListeners();
	}

	private void addListeners()
	{
	}
}