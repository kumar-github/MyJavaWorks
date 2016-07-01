package com.amphorainc.apps.tradeinsert.main;

import com.amphorainc.apps.tradeinsert.controller.TradeInsertLoginController;
import com.amphorainc.apps.tradeinsert.controller.TradeInsertMainController;
import com.amphorainc.apps.tradeinsert.model.TradeInsertLoginModel;
import com.amphorainc.apps.tradeinsert.model.TradeInsertMainModel;
import com.amphorainc.apps.tradeinsert.view.TradeInsertLoginView;
import com.amphorainc.apps.tradeinsert.view.TradeInsertMainView;

public class GUIRunner
{
	TradeInsertLoginController tradeInsertLoginController = null;
	TradeInsertLoginModel tradeInsertLoginModel = null;
	TradeInsertLoginView tradeInsertLoginView = null;
	
	TradeInsertMainController tradeInsertMainController = null;
	TradeInsertMainView tradeInsertMainView = null;
	TradeInsertMainModel tradeInsertMainModel = null;
	
	public GUIRunner()
	{
	}
	
	public void run()
	{
		tradeInsertLoginView = new TradeInsertLoginView();
		//tradeInsertLoginModel = new TradeInsertLoginModel();
		//tradeInsertLoginController = new TradeInsertLoginController(tradeInsertLoginView, tradeInsertLoginModel);
		
		/*tradeInsertMainView = new TradeInsertMainView();
		tradeInsertMainModel = new TradeInsertMainModel();
		tradeInsertMainController = new TradeInsertMainController(tradeInsertMainView, tradeInsertMainModel);*/
	}
}