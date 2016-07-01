package com.amphorainc.apps.tradeinsert.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.amphorainc.apps.tradeinsert.dao.TradeInsertLoginDAO;
import com.amphorainc.apps.tradeinsert.model.TradeInsertLogin;
import com.amphorainc.apps.tradeinsert.util.ConfigUtil;
import com.amphorainc.apps.tradeinsert.util.ConfigurationCache;

/*
 * This is the Top level Whole Application Controller, It creates the other controllers, and in many ways then turns control over to these other 
 * controllers.
 * 
 * It does the database connection stuff it needs to do, constructs the main frame of the application, and displays a login dialog to the user.
 * 
 * Besides doing those things, it also knows about all of its sub-controllers.
*/

public class TradeInsertApplicationController
{
	private static final ConfigurationCache configurationCache;
	
	private TradeInsertLoginController tradeInsertLoginController;
	private TradeInsertMainController tradeInsertMainController;
	//private TradeInsertPreferenceController tradeInsertPreferenceController;
	
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
		//shouldShowLoginScreen();
	}
	
	public TradeInsertApplicationController()
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
		
		shouldShowLoginScreen();
		ConfigUtil.loadAndRegisterCustomFonts();
		
		tradeInsertLoginController = new TradeInsertLoginController(this);
		tradeInsertMainController = new TradeInsertMainController(this);
		//tradeInsertPreferenceController = new TradeInsertPreferenceController(this);
	}

	public void launchApplication()
	{
		if(shouldShowLoginScreen)
		{
			tradeInsertLoginController.showGUI();
		}
		else
		{
			this.authenticateAndShowGUI();
		}
	}
	
	private static boolean firstTime = true;
	private static boolean shouldShowLoginScreen = true;
	private static boolean shouldShowLoginScreen()
	{
		if(firstTime)
		{
			firstTime = false;
			File loginDetailsFile = null;
			try
			{
				loginDetailsFile = new File(ConfigUtil.CREDENTIAL_FILE);
				shouldShowLoginScreen = (loginDetailsFile != null && loginDetailsFile.exists()) ? false : true;
			}
			catch(NullPointerException ex)
			{
				ex.printStackTrace();
			}
		}
		return shouldShowLoginScreen;
	}
	
	/*private boolean authenticateUser()
	{
		boolean authenticationStatus = false;
		
		TradeInsertLogin tradeInsertLoginData = retrieveLoginDetailsFromFile();
		
		if(tradeInsertLoginData != null)
			TradeInsertLoginDAO.authenticateUser(tradeInsertLoginData);
		
		return authenticationStatus;
	}*/
	
	private TradeInsertLogin retrieveLoginDetailsFromFile()
	{
		ObjectInputStream objectReader = null;
		try
		{
			objectReader = new ObjectInputStream(new FileInputStream(ConfigUtil.CREDENTIAL_FILE));
			return (TradeInsertLogin)objectReader.readObject();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				objectReader.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private void authenticateAndShowGUI()
	{
		boolean authenticationStatus = false;
		TradeInsertLogin tradeInsertLoginData = retrieveLoginDetailsFromFile();

		if(tradeInsertLoginData != null)
			authenticationStatus = TradeInsertLoginDAO.authenticateUser(tradeInsertLoginData);
		
		if(authenticationStatus)
		{
			tradeInsertMainController.showGUI();
		}
		else
		{
			/* We are here bcoz, the credential file exists but with invalid data, so better delete the file. */
			deleteCredentialFile();
			tradeInsertLoginController.showGUI();
		}
	}
	
	public void deleteCredentialFile()
	{
		File fileToDelete = new File(ConfigUtil.CREDENTIAL_FILE);
		if(fileToDelete != null)
			fileToDelete.delete();
	}
}