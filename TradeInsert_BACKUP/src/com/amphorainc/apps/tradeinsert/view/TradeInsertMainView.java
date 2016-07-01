package com.amphorainc.apps.tradeinsert.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;

import com.amphorainc.apps.tradeinsert.util.ConfigUtil;
import com.amphorainc.apps.tradeinsert.util.ConfigurationCache;

public class TradeInsertMainView extends JFrame
{
	private static final ConfigurationCache configurationCache;
	
	private JMenuBar menubar;
	
	private JMenu mnuInfo; 
	private JMenu mnuExit;
	
	private JMenuItem mnuitemInfoPanel;
	private JMenuItem mnuitemPreferences;
	
	private JPanel panelForTitle;
	private JLabel lblTitle;
	
	private JPanel panelForFileNameBrowseButton;
	private JLabel lblFileName;
	private JTextField txtFileName;
	private JButton btnBrowse;
	
	private JPanel panelForLogLoadExitButtons;
	private JLabel lblLog;
	private JTextArea txtLog;
	private JButton btnLoadTrades;
	private JButton btnExitApplication;
	
	static
	{
		configurationCache = ConfigurationCache.getInstance();
	}	

	/**
	 * Create the application.
	 */
	public TradeInsertMainView()
	{
		ConfigUtil.setDefaultFont();
		//createAndShowGUI();
		createGUI();
	}

	//private void createAndShowGUI()
	private void createGUI()
	{
		initLookAndFeel();
		createComponents();
		
		//setVisible(true);
	}

	private void initLookAndFeel()
	{
		try
		{
			/*the below line sets the default LookAndFeel. but it will be reset by the LookAndFeel mentioned by the user in the config file*/
			UIManager.setLookAndFeel(configurationCache.getLookAndFeelClassByName(ConfigUtil.DEFAULT_LOOKANDFEEL_NAME));
			
			
			String lookAndFeelNameFromConfigFile = configurationCache.getProperty("DefaultLookAndFeelName");
			if(lookAndFeelNameFromConfigFile != null && !lookAndFeelNameFromConfigFile.equals(""))
			{
				UIManager.setLookAndFeel(configurationCache.getLookAndFeelClassByName(lookAndFeelNameFromConfigFile));
			}
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
		catch(NullPointerException e)
		{
			//we are here bcoz, the user mentioned LookAndFeel is not available. so better set the default LookAndFeel
			e.printStackTrace();
		}
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void createComponents()
	{
		/* ********************* frame setup started *************************** */
		
		this.setTitle(ConfigUtil.APP_NAME);
		this.setSize(ConfigUtil.MAIN_WINDOW_WIDTH, ConfigUtil.MAIN_WINDOW_HEIGHT);
		this.setLocation(ConfigUtil.getCenteredX(this), ConfigUtil.getCenteredY(this));
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
		
		/*create the title label*/
		lblTitle = new JLabel("TRADE INSERT APPLICATION");
		lblTitle.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		lblTitle.setSize(lblTitle.getPreferredSize());
		
		/*the above created title label should be put in a panel, so lets create a penel and add the label in it*/
		panelForTitle = new JPanel();
		panelForTitle.setSize(250, 30);
		panelForTitle.setLocation(ConfigUtil.getCenteredX(this, panelForTitle), 10);
		panelForTitle.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		
		/*add the title label in the panel*/
		panelForTitle.add(lblTitle);
		
		/*add the panel in the frame*/
		getContentPane().add(panelForTitle);
		
		/* ********************* container panel setup completed *************************** */
		
		/* ********************* main window controls setup started *************************** */
		
		panelForFileNameBrowseButton = new JPanel();
		panelForFileNameBrowseButton.setSize(720, 40);
		panelForFileNameBrowseButton.setLocation(ConfigUtil.getCenteredX(this, panelForFileNameBrowseButton), 50);
		panelForFileNameBrowseButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		panelForFileNameBrowseButton.setLayout(null);
		
		lblFileName = new JLabel("Trade FileName : ");
		lblFileName.setSize(lblFileName.getPreferredSize());
		lblFileName.setLocation(10, ConfigUtil.getCenteredY(panelForFileNameBrowseButton, lblFileName));
		panelForFileNameBrowseButton.add(lblFileName);
		
		txtFileName = new JTextField("ENTER FILE NAME", 45);
		txtFileName.setSize(txtFileName.getPreferredSize());
		txtFileName.setLocation(115, ConfigUtil.getCenteredY(panelForFileNameBrowseButton, txtFileName));
		panelForFileNameBrowseButton.add(txtFileName);
		
		btnBrowse = new JButton("Browse...");
		btnBrowse.setSize(btnBrowse.getPreferredSize());
		btnBrowse.setLocation(625, ConfigUtil.getCenteredY(panelForFileNameBrowseButton, btnBrowse));
		panelForFileNameBrowseButton.add(btnBrowse);
		
		getContentPane().add(panelForFileNameBrowseButton);
		
		/* ********************* main window controls setup started *************************** */
		
		panelForLogLoadExitButtons = new JPanel();
		panelForLogLoadExitButtons.setSize(720, 435);
		panelForLogLoadExitButtons.setLocation(ConfigUtil.getCenteredX(this, panelForLogLoadExitButtons), 100);
		panelForLogLoadExitButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		panelForLogLoadExitButtons.setLayout(null);
		
		lblLog = new JLabel("Trade Insert Log");
		lblLog.setSize(lblLog.getPreferredSize());
		lblLog.setLocation(ConfigUtil.getCenteredX(panelForLogLoadExitButtons, lblLog), 10);
		panelForLogLoadExitButtons.add(lblLog);
		
		txtLog = new JTextArea(23,62);
		txtLog.setSize(txtLog.getPreferredSize());
		txtLog.setLocation(ConfigUtil.getCenteredX(panelForLogLoadExitButtons, txtLog), ConfigUtil.getCenteredY(panelForLogLoadExitButtons, txtLog));
		txtLog.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		txtLog.setBackground(Color.LIGHT_GRAY);
		panelForLogLoadExitButtons.add(txtLog);
		
		btnLoadTrades = new JButton("LOAD TRADES");
		btnLoadTrades.setSize(btnLoadTrades.getPreferredSize());
		btnLoadTrades.setLocation(20, 400);
		panelForLogLoadExitButtons.add(btnLoadTrades);
		
		btnExitApplication = new JButton("EXIT APPLICATION");
		btnExitApplication.setSize(btnExitApplication.getPreferredSize());
		btnExitApplication.setLocation(570, 400);
		panelForLogLoadExitButtons.add(btnExitApplication);
		
		getContentPane().add(panelForLogLoadExitButtons);
		
		/* ********************* main window controls setup completed *************************** */
	}

	public void showGUI()
	{
		this.setVisible(true);
	}
	
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
					TradeInsertMainView tradeInsertMainView = new TradeInsertMainView();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}