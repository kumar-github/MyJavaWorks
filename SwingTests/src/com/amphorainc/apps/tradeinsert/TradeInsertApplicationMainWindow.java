package com.amphorainc.apps.tradeinsert;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class TradeInsertApplicationMainWindow
{
	private JFrame tradeInsertApplicationMainWindow;
	private static final Dimension screenSize;
	private static final int screenWidth;
	private static final int screenHeight;
	
	static
	{
		 screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		 screenWidth = screenSize.width;
		 screenHeight = screenSize.height;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TradeInsertApplicationMainWindow window = new TradeInsertApplicationMainWindow();
					window.tradeInsertApplicationMainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TradeInsertApplicationMainWindow() throws Exception
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws Exception
	{
		//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		
		
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		this.changeLF();
		
		tradeInsertApplicationMainWindow = new JFrame();
		//tradeInsertApplicationMainWindow.setBounds(100, 100, 450, 300);
		tradeInsertApplicationMainWindow.setSize(400, 250);
		tradeInsertApplicationMainWindow.setLocation((screenWidth - tradeInsertApplicationMainWindow.getSize().width) / 2, (screenHeight - tradeInsertApplicationMainWindow.getSize().height) / 2);
		tradeInsertApplicationMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tradeInsertApplicationMainWindow.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(94, 113, 112, 14);
		Font font = lblNewLabel.getFont();
		font.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
		tradeInsertApplicationMainWindow.getContentPane().add(lblNewLabel);
	}
	
	public void changeLF() {

        List<String> lookAndFeelsDisplay = new ArrayList<>();
        List<String> lookAndFeelsRealNames = new ArrayList<>();

        for (LookAndFeelInfo each : UIManager.getInstalledLookAndFeels()) {
            lookAndFeelsDisplay.add(each.getName());
            lookAndFeelsRealNames.add(each.getClassName());
        }

        String changeLook = (String) JOptionPane.showInputDialog(null, "Choose Look and Feel Here:", "Select Look and Feel", JOptionPane.QUESTION_MESSAGE, null, lookAndFeelsDisplay.toArray(), null);

        if (changeLook != null) {
            for (int a = 0; a < lookAndFeelsDisplay.size(); a++) {
                if (changeLook.equals(lookAndFeelsDisplay.get(a))) {
                    try {
                        UIManager.setLookAndFeel(lookAndFeelsRealNames.get(a));
                        break;
                    }
                    catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        //err.println(ex);
                        ex.printStackTrace(System.err);
                    }
                }
            }
        }
    }
}