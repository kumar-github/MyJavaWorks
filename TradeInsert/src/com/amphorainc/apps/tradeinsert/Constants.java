package com.amphorainc.apps.tradeinsert;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Constants
{
	public static final Dimension actualScreenSize;
	public static final int actualScreenWidth;
	public static final int actualScreenHeight;
	public static final int loginScreenWidth;
	public static final int loginScreenHeight;
	public static final int mainWindowWidth;
	public static final int mainWindowHeight;
	public static final String appName;
	
	static
	{
		appName = "TradeInsert";
		
		actualScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		actualScreenWidth = actualScreenSize.width;
		actualScreenHeight = actualScreenSize.height;
		
		loginScreenWidth = 400;
		loginScreenHeight = 250;
		
		mainWindowWidth = 800;
		mainWindowHeight = 600;
	}
	
	public Constants()
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
		int x = (Constants.actualScreenWidth - theComponent.getSize().width) / 2;
		return x;
	}
	
	public static int getCenteredY(Component theComponent)
	{
		//here assume screen is the parent
		int y = (Constants.actualScreenHeight - theComponent.getSize().height) / 2;
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
}