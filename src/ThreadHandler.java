import java.util.TimerTask;

/*
 * This class is designed to handle any function of our program that needs to run simultaneously
 * with other functions. This includes most importantly our simulator function. It is also
 * designed to handle any case in which we would want to start or stop a thread.
 */
public class ThreadHandler extends TimerTask {

	AButton theMagicButton;		//The Play Pause button in the MainWindow
	AButton buttonsArray[][];	//The grid where everything happens
	Thread Brain;				//Thread for the logic simulation that turns on/off buttons
	
	public ThreadHandler(AButton button, AButton theGrid[][]){
		//Get what we need from MainWindow
		theMagicButton = button;
		buttonsArray = theGrid;
	}
	
	//Run Method runs whenever the timer calls it, which is once every 100 ms
	public void run() {
		try{
			getPlayPauseState();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//We check the PlayPause button (theMagicButton) 
	private void getPlayPauseState()
	{
		if(theMagicButton.getButtonState() == true)
		{
			//If the button is true, we start a new simulation thread
			Brain = new Thread(new LifeSimulator(100, buttonsArray));	
			Brain.start();
		}
		else if(theMagicButton.getButtonState() == false)
		{
			//If its false, we stop that thread
				//TO-DO: Find a better solution that doesn't require using a deprecated method?
			Brain.stop();
		}	
	}
}