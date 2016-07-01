package com.amphorainc.apps.tradeinsert.view;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.amphorainc.apps.tradeinsert.util.ConfigUtil;
import com.amphorainc.apps.tradeinsert.util.ConfigurationCache;

public class TradeInsertInfoView extends JDialog
{
	private static final ConfigurationCache configurationCache;
	
	static
	{
		configurationCache = ConfigurationCache.getInstance();
	}
	
	public TradeInsertInfoView()
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
		
		this.setTitle(ConfigUtil.APP_NAME + " Info");
		
		this.setSize(320, 200);
		this.setLocation(ConfigUtil.getCenteredX(this), ConfigUtil.getCenteredY(this));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		this.getContentPane().setLayout(null);
		
		/* ********************* frame setup completed *************************** */
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
				new TradeInsertInfoView().showGUI();
			}
		});
	}
}