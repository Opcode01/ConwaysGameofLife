import java.util.TimerTask;
import javax.swing.JSlider;

/*
 * This class is designed to handle any function of our program that needs to run simultaneously
 * with other functions. This includes most importantly our simulator function. It is also
 * designed to handle any case in which we would want to start or stop a thread.
 */
public class ThreadHandler extends TimerTask {

	AButton theMagicButton;		//The Play Pause button in the MainWindow
	AButton buttonsArray[][];	//The grid where everything happens
	JSlider theSlider;			//The slider that controls how long we sleep for
	Thread Brain;				//Thread for the logic simulation that turns on/off buttons
	
	public ThreadHandler(AButton button, AButton theGrid[][], JSlider Slider){
		//Get what we need from MainWindow
		theMagicButton = button;
		buttonsArray = theGrid;
		theSlider = Slider;
	}
	
	//Run Method runs whenever the timer calls it, and then sleep for the amount of time defined by the slider
	public void run() {
		try{
			Brain = new Thread(new LifeSimulator(buttonsArray));	
			Brain.start();
			getPlayPauseState();
			Thread.sleep(theSlider.getValue());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//We check the PlayPause button (theMagicButton) 
	private void getPlayPauseState()
	{
		if(theMagicButton.getButtonState() == true && Brain.isAlive() == false)
		{
			//If the button is true, we start a new simulation thread
			Brain.start();
		}
		else if(theMagicButton.getButtonState() == false)
		{
			//If its false, we stop that thread
				//TO-DO: Find a better solution that doesn't require using a deprecated method?
			if(Brain.isAlive()){
				Brain.stop();
			}
		}	
	}
}