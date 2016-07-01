package com.amphorainc.apps.tradeinsert.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import com.amphorainc.apps.tradeinsert.model.TradeInsertLoginData;
import com.amphorainc.apps.tradeinsert.model.TradeInsertLoginModel;
import com.amphorainc.apps.tradeinsert.model.TradeInsertMainModel;
import com.amphorainc.apps.tradeinsert.util.ConfigUtil;
import com.amphorainc.apps.tradeinsert.util.DBConnectionUtil;
import com.amphorainc.apps.tradeinsert.view.TradeInsertLoginView;
import com.amphorainc.apps.tradeinsert.view.TradeInsertMainView;

public class TradeInsertLoginController
{
	TradeInsertLoginView tradeInsertLoginView;
	TradeInsertLoginModel tradeInsertLoginModel;
	
	TradeInsertMainView tradeInsertMainView;
	TradeInsertMainModel tradeInsertMainModel;
	
	static{}
	
	public TradeInsertLoginController(){}
	
	public TradeInsertLoginController(TradeInsertLoginView tradeInsertLoginView, TradeInsertLoginModel tradeInsertLoginModel)
	{
		this.tradeInsertLoginView = tradeInsertLoginView;
		this.tradeInsertLoginModel = tradeInsertLoginModel;
		
		addListeners();
	}

	private void addListeners()
	{
		/* the below is the way, how we implemented initially. having a separate listener class.
		 * Having a separate listener class makes the code more readable, but the problem is, from 
		 * the listener we can cannot call any controller methods which we really need to access the model.  
		*/
		//this.tradeInsertLoginView.addButtonListener(new LoginCancelButtonListener());
		//this.tradeInsertLoginView.addCheckboxListener(new CheckboxListener());
		
		/* the below is the way, how we implemented by sending the controller to the listener classes so that
		 * we can call the controller method from the listener class
		*/
		//this.tradeInsertLoginView.addButtonListener(new LoginCancelButtonListener(this));
		//this.tradeInsertLoginView.addCheckboxListener(new CheckboxListener(this));
		
		/* the below is the way, we finally decided (atleast for now) making the listeners as Anonymous Inner Classes */
		this.tradeInsertLoginView.getLoginButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				boolean isChecked = tradeInsertLoginView.getSaveLogonInfoCheckboxState();
				JButton btnLogin = (JButton)actionEvent.getSource();
				
				if(btnLogin.isShowing())
					logonButtonClicked(isChecked, true);
				else
					logonButtonClicked(isChecked, false);
			}
		});

		this.tradeInsertLoginView.getCancelButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				cancelButtonClicked();
			}
		});
		
		this.tradeInsertLoginView.getSaveLogonInfoCheckbox().addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
			}
		});
	}
	
	private void logonButtonClicked(boolean saveLoginDetails)
	{
		String dataSource = this.tradeInsertLoginView.getDataSourceFromDataSourceTextField();
		String username = this.tradeInsertLoginView.getUsernameFromUsernameTextField();
		String password = String.valueOf(this.tradeInsertLoginView.getPasswordFromPasswordTextField());

		if(isAnyStringReallyNotEmpty(dataSource, username, password))
		{
			if(saveLoginDetails)
			{
				saveLoginDetails(dataSource, username, password);
			}

			boolean canLogin = authenticateLogin(username, password);

			if(canLogin)
			{
				this.tradeInsertLoginView.dispose();

				tradeInsertMainView = new TradeInsertMainView();
				tradeInsertMainModel = new TradeInsertMainModel();
				TradeInsertMainController tradeInsertMainController = new TradeInsertMainController(tradeInsertMainView, tradeInsertMainModel);

				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						tradeInsertMainView.showGUI();
					}
				});

			}
			else
			{
				System.out.println("Unable to login...");
			}
		}
		else
		{
			System.out.println("Missing login details...");
		}
	}
	
	private void logonButtonClicked(boolean saveLoginDetails, boolean isUserClicked)
	{
		String dataSource = ""; 
		String username = "";
		String password = "";
		
		if(isUserClicked)
		{
			dataSource = this.tradeInsertLoginView.getDataSourceFromDataSourceTextField();
			username = this.tradeInsertLoginView.getUsernameFromUsernameTextField();
			password = String.valueOf(this.tradeInsertLoginView.getPasswordFromPasswordTextField());
		}
		else
		{
			//get it from the file
			TradeInsertLoginData tradeInsertLoginData = retrieveLoginCredential();
			if(tradeInsertLoginData != null)
			{
				dataSource = tradeInsertLoginData.getDataSourceName();
				username = tradeInsertLoginData.getUsername();
				password = tradeInsertLoginData.getPassword();
			}
		}

		if(isAnyStringReallyNotEmpty(dataSource, username, password))
		{
			if(saveLoginDetails)
			{
				saveLoginDetails(dataSource, username, password);
			}

			boolean canLogin = authenticateLogin(username, password);

			if(canLogin)
			{
				this.tradeInsertLoginView.dispose();

				tradeInsertMainView = new TradeInsertMainView();
				tradeInsertMainModel = new TradeInsertMainModel();
				TradeInsertMainController tradeInsertMainController = new TradeInsertMainController(tradeInsertMainView, tradeInsertMainModel);

				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						tradeInsertMainView.showGUI();
					}
				});

			}
			else
			{
				System.out.println("Unable to login...");
			}
		}
		else
		{
			System.out.println("Missing login details...");
		}
	}
	
	private void cancelButtonClicked()
	{
		//this.tradeInsertLoginView.dispose();
		System.exit(0);
	}
	
	//public static boolean authenticateLogin(String connectionUrl, String username, String password)
	public static boolean authenticateLogin(String username, String password)
	{
		//return DBConnectionUtil.authenticateUser(ConfigUtil.CONNECTION_URL, username, password);
		return DBConnectionUtil.authenticate(username, password);
	}
	
	private void saveLoginDetails(String dataSource, String username, String password)
	{
		ObjectOutputStream objectWriter = null;
		
		try
		{
			objectWriter = new ObjectOutputStream(new FileOutputStream(ConfigUtil.CREDENTIAL_FILE_LOCATION + "\\" + ConfigUtil.CREDENTIAL_FILE_NAME));
			objectWriter.writeObject(new TradeInsertLoginData(dataSource, username, password));
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				objectWriter.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private TradeInsertLoginData retrieveLoginCredential()
	{
		ObjectInputStream objectReader = null;
		try
		{
			objectReader = new ObjectInputStream(new FileInputStream(ConfigUtil.CREDENTIAL_FILE_LOCATION + "\\" + ConfigUtil.CREDENTIAL_FILE_NAME));
			return (TradeInsertLoginData)objectReader.readObject();
			
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
	
	private boolean isStringReallyEmpty(String anyString)
	{
		return (anyString == null || anyString.equals("") || anyString.isEmpty()) ? true : false; 
	}
	
	private boolean isStringReallyNotEmpty(String anyString)
	{
		//return (anyString != null && !anyString.equals("") && anyString.length() != 0) ? true : false;
		return !isStringReallyEmpty(anyString);
	}
	
	private boolean isAnyStringReallyEmpty(String... anyStrings)
	{
		for (String aString : anyStrings)
		{
			if(isStringReallyEmpty(aString))
				return true;
		}
		return false;
	}
	
	private boolean isAnyStringReallyNotEmpty(String... anyStrings)
	{
		return !isAnyStringReallyEmpty(anyStrings);
	}
	
	private boolean areAllStringsReallyEmpty(String... anyStrings)
	{
		for (String aString : anyStrings)
		{
			if(!isStringReallyEmpty(aString))
				return false;
		}
		return true;
	}
	
	/*private boolean canLogin(String dataSource, String username, String password)
	{
		boolean canLogin = false;
		
		//ask model whether the user can login or not
		try
		{
			//canLogin = DBConnectionUtil.authenticateUser(ConfigUtil.CONNECTION_URL, username, password);
			canLogin = DBConnectionUtil.authenticateUser(ConfigUtil.CONNECTION_URL, username, password);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return canLogin;
	}*/
	
	/*
	private class LoginCancelButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent actionEvent)
		{
		}
	}
	
	private class CheckboxListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent itemEvent)
		{
		}
	}
	*/
}