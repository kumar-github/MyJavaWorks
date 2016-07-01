package com.amphorainc.apps.tradeinsert.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.UIManager;

public class ConfigUtil
{
	/*
	public static final Dimension actualScreenSize;
	public static final int actualScreenWidth;
	public static final int actualScreenHeight;
	public static final int loginScreenWidth;
	public static final int loginScreenHeight;
	public static final int mainWindowWidth;
	public static final int mainWindowHeight;
	public static final String appName;
	public static final String defaultLookAndFeelName;

	private static final String fontsDirectory="";
	public static final String defaultFontNormal="";
	public static final String defaultFontBold="";
	public static final String defaultFontBoldItalic="";

	public static final String defaultLabelFont="";
	public static final String defaultTextFieldFont="";
	public static final String defaultButtonFont="";
	public static final String defaultCheckboxFont="";
	*/
	
	public static final Dimension ACTUAL_SCREEN_SIZE;
	public static final int ACTUAL_SCREEN_WIDTH;
	public static final int ACTUAL_SCREEN_HEIGHT;
	public static final int LOGIN_SCREEN_WIDTH;
	public static final int LOGIN_SCREEN_HEIGHT;
	public static final int MAIN_WINDOW_WIDTH;
	public static final int MAIN_WINDOW_HEIGHT;
	public static final String APP_NAME;
	public static final String DEFAULT_LOOKANDFEEL_NAME;

	private static final String FONTS_DIRECTORY;
	public static final String DEFAULT_FONT_NORMAL;
	public static final String DEFAULT_FONT_BOLD="";
	public static final String DEFAULT_FONT_BOLD_ITALIC="";

	public static final String DEFAULT_LABEL_FONT="";
	public static final String DEFAULT_TEXTFIELD_FONT="";
	public static final String DEFAULT_BUTTON_FONT="";
	public static final String DEFAULT_CHECKBOX_FONT="";
	
	public static final String DATABASE_CONNECTION_URL_KEY;
	public static final String DATABASE_DRIVER_CLASS_NAME_KEY;
	public static final String CREDENTIAL_FILE_LOCATION;
	public static final String CREDENTIAL_FILE_NAME;
	
	static
	{
		APP_NAME = "TradeInsert";

		/*set this only, if "DefaultLookAndFeelName" is not set in the config file or if the mentioned LookAndFeel is unavailable*/
		DEFAULT_LOOKANDFEEL_NAME = "Windows";

		ACTUAL_SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
		ACTUAL_SCREEN_WIDTH = ACTUAL_SCREEN_SIZE.width;
		ACTUAL_SCREEN_HEIGHT = ACTUAL_SCREEN_SIZE.height;

		//LOGIN_SCREEN_WIDTH = 400;
		LOGIN_SCREEN_WIDTH = 320;
		LOGIN_SCREEN_HEIGHT = 250;

		MAIN_WINDOW_WIDTH = 800;
		MAIN_WINDOW_HEIGHT = 600;

		FONTS_DIRECTORY = "D:\\MyDropbox\\Dropbox\\My Java Workspace\\TradeInsert\\resources\\fonts\\";
		CREDENTIAL_FILE_LOCATION = System.getProperty("user.home");
		CREDENTIAL_FILE_NAME = "TradeInsertLoginData.ser";
		
		/*
		 * Ubuntu Light, Ubuntu Light Italic, Ubuntu Medium Italic, Ubuntu, Ubuntu Medium, Ubuntu Italic, Ubuntu Bold Italic,
		 * Ubuntu Bold, Consolas, Consolas Bold, Consolas Italic, Consolas Bold Italic
		*/
		DEFAULT_FONT_NORMAL = "Ubuntu";
		
		DATABASE_DRIVER_CLASS_NAME_KEY = "databaseDriverClassName";
		DATABASE_CONNECTION_URL_KEY = "databaseConnectionURL";
	}

	public ConfigUtil()
	{
	}

	/*public static Point getScreenCenterForComponent(Component aComponent)
	{
		int x = (Constants.actualScreenWidth - aComponent.getSize().width) / 2;
		int y = (Constants.actualScreenHeight - aComponent.getSize().height) / 2;

		return new Point(x,y);
	}

	public static Point getScreenCenterForComponent(Component parentComponent, Component aComponent)
	{
		int x = (parentComponent.getSize().width - aComponent.getSize().width) / 2;
		int y = (parentComponent.getSize().height - aComponent.getSize().height) / 2;

		return new Point(x,y);
	}*/

	public static int getCenteredX(Component theComponent)
	{
		//here assume screen is the parent
		int x = (ConfigUtil.ACTUAL_SCREEN_WIDTH - theComponent.getSize().width) / 2;
		return x;
	}

	public static int getCenteredY(Component theComponent)
	{
		//here assume screen is the parent
		int y = (ConfigUtil.ACTUAL_SCREEN_HEIGHT - theComponent.getSize().height) / 2;
		return y;
	}

	public static int getCenteredX(Component theParentComponent, Component theComponent)
	{
		int x = (theParentComponent.getSize().width - theComponent.getSize().width) / 2;
		return x;
	}

	public static int getCenteredY(Component theParentComponent, Component theComponent)
	{
		int y = (theParentComponent.getSize().height - theComponent.getSize().height) / 2;
		return y;
	}

	/*public static Rectangle getRectangleFromWidthHeight(int width, int height)
	{
		return new Rectangle(0, 0, width, height);
	}

	public static Rectangle getRectangleFromXYWidthHeight(int x, int y, int width, int height)
	{
		return new Rectangle(x, y, width, height);
	}*/

	public static void setDefaultFont()
	{
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while(keys.hasMoreElements())
		{
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof javax.swing.plaf.FontUIResource)
				//UIManager.put(key, new Font("Consolas", Font.PLAIN, 11));
				UIManager.put(key, new Font(DEFAULT_FONT_NORMAL, Font.PLAIN, 12));
		}
	}

	public static void loadAndRegisterCustomFonts()
	{
		File fontsDirectory = new File(ConfigUtil.FONTS_DIRECTORY);
		for(File aFontFile : fontsDirectory.listFiles())
		{
			try
			{
				//Font x = Font.createFont(Font.TRUETYPE_FONT, aFontFile).deriveFont(12);
				//GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(x);
				GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, aFontFile).deriveFont(12));
			}
			catch (FontFormatException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}