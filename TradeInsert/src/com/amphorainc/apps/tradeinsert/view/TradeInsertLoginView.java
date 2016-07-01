package com.amphorainc.apps.tradeinsert.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;

import com.amphorainc.apps.tradeinsert.util.ConfigUtil;
import com.amphorainc.apps.tradeinsert.util.ConfigurationCache;

public class TradeInsertLoginView extends JDialog
{
	private static final ConfigurationCache configurationCache;
	
	private JPanel panel;
	private JLabel lblTitle;
	
	private JLabel lblDataSource;
	private JTextField txtDataSource;
	
	private JLabel lblUsername;
	private JTextField txtUsername;
	
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	
	private JCheckBox chkSaveLogonInfo;
	
	private JButton btnLogin;
	private JButton btnCancel;

	static
	{
		configurationCache = ConfigurationCache.getInstance();
	}

	public TradeInsertLoginView()
	{
		//ConfigUtil.loadAndRegisterCustomFonts();
		ConfigUtil.setDefaultFont();
		createGUI();
	}
	
	public void createGUI()
	{
		initLookAndFeel();
		createComponents();
	}

	public void initLookAndFeel()
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
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
	 * Create the dialog.
	 */
	public void createComponents()
	{
		/* ********************* frame setup started *************************** */
		
		this.setTitle(ConfigUtil.APP_NAME);
		
		this.setSize(ConfigUtil.LOGIN_SCREEN_WIDTH, ConfigUtil.LOGIN_SCREEN_HEIGHT);
		this.setLocation(ConfigUtil.getCenteredX(this), ConfigUtil.getCenteredY(this));
		//this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		this.getContentPane().setLayout(null);
		
		/* ********************* frame setup completed *************************** */
		
		/* ********************* container panel setup started *************************** */
		
		lblTitle = new JLabel("Database Connection");
		//lblTitle.setFont(new Font("Consolas", Font.BOLD, 15));
		lblTitle.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		lblTitle.setSize(lblTitle.getPreferredSize());
		lblTitle.setLocation(ConfigUtil.getCenteredX(this, lblTitle), 10);
		getContentPane().add(lblTitle);
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setSize(300, 150);
		panel.setSize(250, 145);
		panel.setLocation(ConfigUtil.getCenteredX(this, panel), 35);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		lblDataSource = new JLabel("DataSource ");
		lblDataSource.setSize(lblDataSource.getPreferredSize());
		lblDataSource.setLocation(20, 20);
		panel.add(lblDataSource);
		
		txtDataSource = new JTextField(12);
		txtDataSource.setSize(txtDataSource.getPreferredSize());
		txtDataSource.setLocation(100, 18);
		panel.add(txtDataSource);
		
		lblUsername = new JLabel("User ID ");
		lblUsername.setSize(lblUsername.getPreferredSize());
		lblUsername.setLocation(20, 54);
		panel.add(lblUsername);
		
		txtUsername = new JTextField(12);
		txtUsername.setSize(txtUsername.getPreferredSize());
		txtUsername.setLocation(100, 50);
		panel.add(txtUsername);
		
		lblPassword = new JLabel("Password ");
		lblPassword.setSize(lblPassword.getPreferredSize());
		lblPassword.setLocation(20, 88);
		panel.add(lblPassword);
		
		//txtPassword = new JTextField(12);
		txtPassword = new JPasswordField(12);
		txtPassword.setEchoChar('*');
		txtPassword.setSize(txtPassword.getPreferredSize());
		txtPassword.setLocation(100, 82);
		panel.add(txtPassword);
		
		chkSaveLogonInfo = new JCheckBox("Save Logon Info");
		chkSaveLogonInfo.setSize(chkSaveLogonInfo.getPreferredSize());
		chkSaveLogonInfo.setLocation(127, 110);
		chkSaveLogonInfo.setFocusPainted(false);
		panel.add(chkSaveLogonInfo);
		
		getContentPane().add(panel);
		
		btnLogin = new JButton("Logon");
		btnLogin.setSize(btnLogin.getPreferredSize());
		btnLogin.setLocation(145, 190);
		getContentPane().add(btnLogin);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setSize(btnCancel.getPreferredSize());
		btnCancel.setLocation(215, 190);
		getContentPane().add(btnCancel);
		
		/* ********************* container panel setup completed *************************** */
	}
	
	public void showGUI()
	{
		this.setVisible(true);
	}

	public String getDataSourceFromDataSourceTextField()
	{
		return txtDataSource.getText();
	}
	
	public String getUsernameFromUsernameTextField()
	{
		return txtUsername.getText();
	}
	
	public char[] getPasswordFromPasswordTextField()
	{
		return txtPassword.getPassword();
	}
	
	public boolean getSaveLogonInfoCheckboxState()
	{
		return chkSaveLogonInfo.isSelected();
	}
	
	/* ************************************************************************************************************* */
	
	/* getters which returns the actual components like button, textfield to the controller so that
	 * the controller can set the listeners on the components.
	*/
	
	public JButton getLoginButton()
	{
		return btnLogin;
	}
	
	public JButton getCancelButton()
	{
		return btnCancel;
	}
	
	public JCheckBox getSaveLogonInfoCheckbox()
	{
		return chkSaveLogonInfo;
	}
	
	/* ************************************************************************************************************* */
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		//Schedule the job for the event dispatch thread. Creating and showing the application's GUI
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new TradeInsertLoginView();
			}
		});
	}
}