import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e)
	{
			System.out.println("Button Listener");
			System.out.println(e.getModifiers());
	}

}
