package com.amphorainc.apps.tradeinsert;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class TradeInsertApplicationMainWindow extends JFrame
{
	private JMenuBar menubar;
	
	private JMenu mnuInfo; 
	private JMenu mnuExit;
	
	private JMenuItem mnuitemInfoPanel;
	private JMenuItem mnuitemPreferences;
	
	private JPanel panel;
	private JLabel lblTitle;
	private JLabel lblFileName;
	private JTextField txtFileName;
	private JButton btnBrowse;
	private JLabel lblLog;
	private JTextArea txtLog;
	private JButton btnLoadTrades;
	private JButton btnExitApplication;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					TradeInsertApplicationMainWindow tradeInsertApplicationMainWindow = new TradeInsertApplicationMainWindow();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TradeInsertApplicationMainWindow()
	{
		createAndShowGUI();
	}

	private void createAndShowGUI()
	{
		initLookAndFeel();
		createComponents();
		
		
		setVisible(true);
	}

	private void initLookAndFeel()
	{
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void createComponents()
	{
		/* ********************* frame setup started *************************** */
		
		this.setTitle(Constants.appName);
		this.setSize(Constants.mainWindowWidth, Constants.mainWindowHeight);
		this.setLocation(Constants.getCenteredX(this), Constants.getCenteredY(this));
		this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		
		/* ********************* frame setup completed *************************** */
		
		/* ********************* menu bar setup started *************************** */
		
		menubar = new JMenuBar();
		
		mnuInfo = new JMenu("Info");
		
		mnuitemInfoPanel = new JMenuItem("Info Panel");
		mnuitemPreferences = new JMenuItem("Preferences");
		
		mnuInfo.add(mnuitemInfoPanel);
		mnuInfo.add(mnuitemPreferences);
		
		mnuExit = new JMenu("Exit");
		
		menubar.add(mnuInfo);
		menubar.add(mnuExit);
		
		setJMenuBar(menubar);
		
		/* ********************* menu bar setup completed *************************** */
		
		/* ********************* container panel setup started *************************** */
		
		panel = new JPanel();
		panel.setSize(200, 30);
		panel.setLocation(Constants.getCenteredX(this, panel), 10);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		
		lblTitle = new JLabel("TRADE INSERT APPLICATION");
		panel.add(lblTitle);
		
		getContentPane().add(panel);
		
		/* ********************* container panel setup completed *************************** */
		
		/* ********************* main window controls setup started *************************** */
		
		lblFileName = new JLabel("Trade FileName : ");
		lblFileName.setBounds(60, 80, 100, 20);
		getContentPane().add(lblFileName);
		
		txtFileName = new JTextField("ENTER FILE NAME");
		txtFileName.setBounds(150, 80, 300, 20);
		getContentPane().add(txtFileName);
		
		btnBrowse = new JButton("Browse...");
		btnBrowse.setBounds(460, 80, 80, 20);
		getContentPane().add(btnBrowse);
		
		lblLog = new JLabel("Trade Insert Log");
		lblLog.setBounds(250, 120, 80, 20);
		getContentPane().add(lblLog);
		
		txtLog = new JTextArea(20,50);
		txtLog.setBounds(50, 150, 600, 300);
		getContentPane().add(txtLog);
		
		btnLoadTrades = new JButton("LOAD TRADES");
		btnLoadTrades.setBounds(100,480, 120, 20);
		getContentPane().add(btnLoadTrades);
		
		btnExitApplication = new JButton("EXIT APPLICATION");
		btnExitApplication.setBounds(500,480, 130, 20);
		getContentPane().add(btnExitApplication);
		
		/* ********************* main window controls setup completed *************************** */
	}
}