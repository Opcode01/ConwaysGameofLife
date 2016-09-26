
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AButton extends JButton implements ActionListener{

	ImageIcon On, Off;
	boolean ButtonState = false;
	
	//Constructor allows for custom images to be added
	public AButton(ImageIcon onImg, ImageIcon offImg)
	{
		On = onImg;
		Off = offImg;
		this.addActionListener(this);
	}
	//Constructor can be used with default images as well
	public AButton()
	{
		this.addActionListener(this);
		On = new ImageIcon(this.getClass().getResource("Black.png"));
		Off = null;
	}
	
	public void actionPerformed(ActionEvent e){
		if(ButtonState == false){
			ButtonState = true;	
			setIcon(On);
		}
		else if(ButtonState == true){
			ButtonState = false;
			setIcon(Off);
		}
	}
	
	//Method used to get the state of a specific button
	public boolean getButtonState(){
		return ButtonState;
	}
	
	//This Method is used by the CPU to set the button as either on or off
	public void setButtonState(boolean setValue){
		ButtonState = setValue;
		if(setValue == true)
			setIcon(On);
		else if(setValue == false)
			setIcon(Off);
	}
	
}