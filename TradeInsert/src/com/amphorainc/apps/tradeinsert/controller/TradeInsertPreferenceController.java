package com.amphorainc.apps.tradeinsert.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.SwingUtilities;

import com.amphorainc.apps.tradeinsert.model.TradeInsertPreferenceModel;
import com.amphorainc.apps.tradeinsert.view.TradeInsertPreferenceView;

public class TradeInsertPreferenceController
{
	TradeInsertApplicationController tradeInsertApplicationController;
	
	TradeInsertPreferenceView tradeInsertPreferenceView;
	TradeInsertPreferenceModel tradeInsertPreferenceModel;
	
	static{}
	
	public TradeInsertPreferenceController()
	{
		//this.tradeInsertPreferenceView = new TradeInsertPreferenceView();
		//this.tradeInsertPreferenceModel = new TradeInsertPreferenceModel();
		
		this(new TradeInsertPreferenceView(), new TradeInsertPreferenceModel());
		
		addListeners();
	}
	
	public TradeInsertPreferenceController(TradeInsertPreferenceView tradeInsertPreferenceView, TradeInsertPreferenceModel tradeInsertPreferenceModel)
	{
		this.tradeInsertPreferenceView = tradeInsertPreferenceView;
		this.tradeInsertPreferenceModel = tradeInsertPreferenceModel;
		
		addListeners();
	}

	public TradeInsertPreferenceController(TradeInsertApplicationController tradeInsertApplicationController)
	{
		//this.tradeInsertApplicationController = tradeInsertApplicationController;
		//this.tradeInsertPreferenceView = new TradeInsertPreferenceView();
		//this.tradeInsertPreferenceModel = new TradeInsertPreferenceModel();
		
		this(new TradeInsertPreferenceView(), new TradeInsertPreferenceModel());
		this.tradeInsertApplicationController = tradeInsertApplicationController;
		
		addListeners();
	}

	private void addListeners()
	{
		this.tradeInsertPreferenceView.addWindowListener(new WindowListener()
		{
			@Override
			public void windowOpened(WindowEvent e)
			{
			}
			
			@Override
			public void windowIconified(WindowEvent e)
			{
			}
			
			@Override
			public void windowDeiconified(WindowEvent e)
			{
			}
			
			@Override
			public void windowDeactivated(WindowEvent e)
			{
			}
			
			@Override
			public void windowClosing(WindowEvent e)
			{
			}
			
			@Override
			public void windowClosed(WindowEvent e)
			{
				applyChanges();
			}

			@Override
			public void windowActivated(WindowEvent e)
			{
			}
		});
		
		this.tradeInsertPreferenceView.getApplyChangesButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				applyChanges();
			}
		});
		
		this.tradeInsertPreferenceView.getCancelButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				disposeTradeInsertPreferenceView();
			}
		});
	}
	
	private void applyChanges()
	{
		boolean needToReset = tradeInsertPreferenceView.getResetLogonInfoCheckboxState();
		String inputDirectory = tradeInsertPreferenceView.getInputDirectoryFromInputDirectoryTextField();
		
		if(needToReset)
		{
			tradeInsertApplicationController.deleteCredentialFile();
		}
		
		disposeTradeInsertPreferenceView();
	}
	
	private void disposeTradeInsertPreferenceView()
	{
		//tradeInsertPreferenceView.dispose();
		tradeInsertPreferenceView.setVisible(false);
	}
	
	public void showGUI()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				tradeInsertPreferenceView.showGUI();
			}
		});
	}
	
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