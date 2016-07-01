import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class MainWindow
{
	public static void main(String[] args)
	{
		JButton button = new JButton("Click");
		JCheckBox check = new JCheckBox("Test");
		JFrame frame = new JFrame();
		frame.setLocation(100, 200);
		frame.setSize(400, 400);
		frame.setLayout(new FlowLayout());
		frame.add(button);
		frame.add(check);
		
		JPanel tab1 = new JPanel();
		tab1.setSize(500,500);
		
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("title", tab1);
		frame.add(pane);
		
		button.addActionListener(new ButtonListener());
		button.addActionListener(new AnotherListener());
		check.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				System.out.println("check box listener");
			}
		});
		frame.setVisible(true);
	}
}
