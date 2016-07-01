package com.amphorainc.apps.tradeinsert;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TradeInsertApplicationLoginDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	
	private static final ConfigurationCache configurationCache;
	
	private JLabel lblTitle;
	private JLabel lblClose;
	private JLabel lblHeading;
	private JLabel lblUsername;
	private JLabel lblPassword;
	
	private JTextField txtUsername;
	private JTextField txtPassword;
	//private JPasswordField txtPassword;
	
	private JButton btnLogin;
	private JButton btnCancel;

	static
	{
		configurationCache = ConfigurationCache.getInstance();
	}

	public TradeInsertApplicationLoginDialog()
	{
		createAndShowGUI();
	}
	
	public void createAndShowGUI()
	{
		initLookAndFeel();
		createComponents();
		setVisible(true);
	}

	public void initLookAndFeel()
	{
		try
		{
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			if(shouldLoadCustomLookAndFeel())
			{
				//load all the custom lafs from the "CustomLookAndFeel.config" file
			}
		}
		catch(Exception e)
		{
		}
	}

	private boolean shouldLoadCustomLookAndFeel()
	{
		if(configurationCache.getProperty("LoadCustomLookAndFeel") != null && configurationCache.getProperty("LoadCustomLookAndFeel").equalsIgnoreCase("yes"))
			return true;
		return false;
	}

	/**
	 * Create the dialog.
	 */
	public void createComponents()
	{
		this.setTitle(Constants.appName);
		this.setSize(Constants.loginScreenWidth, Constants.loginScreenHeight);
		this.setLocation(Constants.getCenteredX(this), Constants.getCenteredY(this));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		this.getContentPane().setLayout(null);
		
		JLabel lblDatabaseConnection = new JLabel("Database Connection");
		lblDatabaseConnection.setLocation(135, 11);
		lblDatabaseConnection.setSize(lblDatabaseConnection.getPreferredSize());
		lblDatabaseConnection.setFont(new Font("Consolas", Font.BOLD, 14));
		getContentPane().add(lblDatabaseConnection);

		JLabel lblDatasource = new JLabel("DataSource");
		//lblDatasource.setBounds(30, 45, 46, 14);
		lblDatasource.setLocation(30, 45);
		lblDatasource.setSize(new Dimension(56, 24));
		getContentPane().add(lblDatasource);

		JLabel lblUserId = new JLabel("User ID");
		//lblUserId.setBounds(30, 80, 46, 14);
		lblUserId.setLocation(30, 80);
		lblUserId.setSize(lblUserId.getPreferredSize());
		getContentPane().add(lblUserId);

		JLabel lblPassword = new JLabel("Password");
		//lblPassword.setBounds(30, 119, 46, 14);
		lblPassword.setLocation(30, 119);
		lblPassword.setSize(lblPassword.getPreferredSize());
		getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(135, 47, 103, 20);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(135, 77, 103, 20);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		/*Schedule the job for the event dispatch thread. Creating and showing the application's GUI*/
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new TradeInsertApplicationLoginDialog();
			}
		});
	}
}