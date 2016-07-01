package com.amphorainc.apps.tradeinsert.main;

import java.io.File;

import javax.swing.SwingUtilities;

import com.amphorainc.apps.tradeinsert.controller.TradeInsertLoginController;
import com.amphorainc.apps.tradeinsert.controller.TradeInsertMainController;
import com.amphorainc.apps.tradeinsert.model.TradeInsertLoginModel;
import com.amphorainc.apps.tradeinsert.model.TradeInsertMainModel;
import com.amphorainc.apps.tradeinsert.util.ConfigUtil;
import com.amphorainc.apps.tradeinsert.util.ConfigurationCache;
import com.amphorainc.apps.tradeinsert.view.TradeInsertLoginView;
import com.amphorainc.apps.tradeinsert.view.TradeInsertMainView;

public class GUIRunner
{
	private static final ConfigurationCache configurationCache;
	
	private TradeInsertLoginController tradeInsertLoginController = null;
	private TradeInsertLoginModel tradeInsertLoginModel = null;
	private TradeInsertLoginView tradeInsertLoginView = null;
	
	private TradeInsertMainController tradeInsertMainController = null;
	private TradeInsertMainView tradeInsertMainView = null;
	private TradeInsertMainModel tradeInsertMainModel = null;
	
	//private File loginDetailsFile = null;
	//private boolean shouldShowLoginScreen = true;
	
	static
	{
		/*
		 * whatever has to be done before loading the GUI, eg: loading the config data, loading the look and feel, loading the fonts etc. 
		 * should be done here. This is irrespective to the window which we display eg: Login Window or Main Window. If something is
		 * specific to the particular window then do that at the specific window loading time.
		 */
		
		/* The below line gives a singleton instance of the "ConfigurationCache" class. In the static block we load all the configurations
		 * by reading it from the xml file and put it in the configProperties hashmap so that we can access it whenever we need. Also we 
		 * load the system LookAndFeels and put it in the  configProperties hashmap. So eventually at the end of the below line execution,
		 * we have all the configurations loaded and kept in the configProperties hashmap.
		*/
		configurationCache = ConfigurationCache.getInstance();
	}
	
	public GUIRunner()
	{
		/*
		 * whatever has to be done before loading the GUI, eg: loading the config data, loading the look and feel, loading the fonts etc. 
		 * should be done here. This is irrespective to the window which we display eg: Login Window or Main Window. If something is
		 * specific to the particular window then do that at the specific window loading time.
		 */
		
		/*
		 * The below method loads all the custom fonts and also register the fonts to the current local Graphics Environment, so that
		 * it can be used in the future setFont method calls as if those fonts are available in the system by default.
		 * 
		 * NOTE: this will only load and register the fonts but to set the fonts to all the components in a window, we need to call
		 * ConfigUtil.setDefaultFont() method from the respective window. 
		 */
		ConfigUtil.loadAndRegisterCustomFonts();
		
//		try
//		{
//			loginDetailsFile = new File(ConfigUtil.CREDENTIAL_FILE_LOCATION + "\\" + ConfigUtil.CREDENTIAL_FILE_NAME);
//			shouldShowLoginScreen = (loginDetailsFile != null && loginDetailsFile.exists()) ? false : true;
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
	}
	
	public void launchApplication()
	{
		/*tradeInsertLoginView = new TradeInsertLoginView();
		tradeInsertLoginModel = new TradeInsertLoginModel();
		tradeInsertLoginController = new TradeInsertLoginController(tradeInsertLoginView, tradeInsertLoginModel);
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				tradeInsertLoginView.showGUI();
			}
		});*/

		
		/*tradeInsertMainView = new TradeInsertMainView();
		tradeInsertMainModel = new TradeInsertMainModel();
		tradeInsertMainController = new TradeInsertMainController(tradeInsertMainView, tradeInsertMainModel);
		
		//need to decide here whether to show the login screen or not
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				tradeInsertMainView.showGUI();
			}
		});*/
		
		/*if(shouldShowLoginScreen)
		{
			tradeInsertLoginView = new TradeInsertLoginView();
			tradeInsertLoginModel = new TradeInsertLoginModel();
			tradeInsertLoginController = new TradeInsertLoginController(tradeInsertLoginView, tradeInsertLoginModel);
			
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					tradeInsertLoginView.showGUI();
				}
			});
		}
		else
		{*/
			/*
			 * don't show the login screen but need to validate the login details in the file, and proceed to main application if login details
			 * are valid, if invalid show the login screen.
			 */
			
			/*tradeInsertMainView = new TradeInsertMainView();
			tradeInsertMainModel = new TradeInsertMainModel();
			tradeInsertMainController = new TradeInsertMainController(tradeInsertMainView, tradeInsertMainModel);
			
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					tradeInsertMainView.showGUI();
				}
			});
		}*/
		
		tradeInsertLoginView = new TradeInsertLoginView();
		tradeInsertLoginModel = new TradeInsertLoginModel();
		tradeInsertLoginController = new TradeInsertLoginController(tradeInsertLoginView, tradeInsertLoginModel);
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				//tradeInsertLoginView.showGUI();
				tradeInsertLoginView.showGUIIfNeeded();
			}
		});
	}
}