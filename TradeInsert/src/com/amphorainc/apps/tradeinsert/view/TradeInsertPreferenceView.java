package com.amphorainc.apps.tradeinsert.view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.amphorainc.apps.tradeinsert.util.ConfigUtil;
import com.amphorainc.apps.tradeinsert.util.ConfigurationCache;

//public class TradeInsertPreferenceView extends JFrame
public class TradeInsertPreferenceView extends JDialog
{
	private static final ConfigurationCache configurationCache;
	
	private JCheckBox chkResetLogonInfo;
	private JLabel lblInputDirectory;
	private JTextField txtInputDirectory;
	
	private JButton btnApplyChanges;
	private JButton btnCancel;
	
	static
	{
		configurationCache = ConfigurationCache.getInstance();
	}
	
	public TradeInsertPreferenceView()
	{
		ConfigUtil.setDefaultFont();
		createGUI();
	}
	
	public void createGUI()
	{
		initLookAndFeel();
		createComponents();
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
	
	private void createComponents()
	{
		/* ********************* frame setup started *************************** */
		
		this.setTitle(ConfigUtil.APP_NAME + " Preferences");
		
		this.setSize(320, 200);
		this.setLocation(ConfigUtil.getCenteredX(this), ConfigUtil.getCenteredY(this));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		this.getContentPane().setLayout(null);
		
		/* ********************* frame setup completed *************************** */
		
		/*panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(250, 100);
		panel.setLocation(ConfigUtil.getCenteredX(this, panel), ConfigUtil.getCenteredY(this, panel));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));*/
		
		chkResetLogonInfo = new JCheckBox("Reset Logon Credentials");
		chkResetLogonInfo.setSize(chkResetLogonInfo.getPreferredSize());
		chkResetLogonInfo.setLocation(10, 10);
		chkResetLogonInfo.setFocusPainted(false);
		this.add(chkResetLogonInfo);
		
		lblInputDirectory = new JLabel("Input Directory");
		lblInputDirectory.setSize(lblInputDirectory.getPreferredSize());
		lblInputDirectory.setLocation(16, 47);
		this.add(lblInputDirectory);
		
		txtInputDirectory = new JTextField(17);
		txtInputDirectory.setSize(txtInputDirectory.getPreferredSize());
		txtInputDirectory.setLocation(110, 45);
		this.add(txtInputDirectory);
		
		btnApplyChanges = new JButton("Apply Changes");
		btnApplyChanges.setSize(btnApplyChanges.getPreferredSize());
		btnApplyChanges.setLocation(70, 140);
		this.add(btnApplyChanges);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setSize(btnCancel.getPreferredSize());
		btnCancel.setLocation(185, 140);
		this.add(btnCancel);
	}
	
	public JButton getApplyChangesButton()
	{
		return btnApplyChanges;
	}
	
	public JButton getCancelButton()
	{
		return btnCancel;
	}
	
	public boolean getResetLogonInfoCheckboxState()
	{
		return chkResetLogonInfo.isSelected();
	}
	
	public String getInputDirectoryFromInputDirectoryTextField()
	{
		return txtInputDirectory.getText();
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
		//Schedule the job for the event dispatch thread. Creating and showing the application's GUI
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new TradeInsertPreferenceView().showGUI();
			}
		});
	}
}