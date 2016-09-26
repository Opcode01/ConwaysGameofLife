import java.util.*;

/*
This class handles all the logic for the game of life simulator. 
Its the 'Brain' of the game of life. 
Designed to run in a thread
*/

public class LifeSimulator implements Runnable {
	
	int time;
	AButton buttons[][];
	
	public LifeSimulator(int sleepTime, AButton array[][])
	{
		//Get what we need from the ThreadHandler, which gets it from MainWindow
		time = sleepTime;
		buttons = array;
	}
	
	public void run(){
			try {
				Simulator();
				Thread.sleep(time);
				//TO-DO: Add functionality so the user can adjust the speed of the simulation using
				//the time = sleepTime variable
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	//The really complicated method that runs the logic
	private void Simulator(){
		
		boolean selfCheck;	//State of the button we are checking	
		int aliveNeighbors = 0;	//Value we get from the checkButtons function
		
			for(int i = 0; i < 25; i++ )
			{
				for(int j = 0; j < 25; j++)
				{
					//Check the state of this AButton
					selfCheck = buttons[i][j].getButtonState();
					if(selfCheck){
						//If this cell is alive, check for the neighbors
						aliveNeighbors = checkButtons(i, j) - 1;	//We don't want to count ourself, so do checkButtons - 1
						System.out.println(aliveNeighbors);
						//Live cell with less than 2 neighbors dies
						if(aliveNeighbors < 2)
							buttons[i][j].setButtonState(false);
						//Live cell with more than 3 neighbors dies
						else if(aliveNeighbors > 3)
							buttons[i][j].setButtonState(false);
						//If live cell with 2 or 3 neighbors, do nothing
						
					}	
					if(!selfCheck){
						//If the cell is dead, check for exactly 3 active neighbors
						aliveNeighbors = checkButtons(i,j);		//This time, it doesn't matter if we count ourselves
						System.out.println(aliveNeighbors);
						if(aliveNeighbors == 3)
							buttons[i][j].setButtonState(true);
					}	
				}
			}
	}

	//This method is called from Simulator() to check the neighbors around our button
	private int checkButtons(int indexI, int indexJ){
			
		int AliveCells = 0;
		//We use nested for loop to check from the upper left of the center button, 
		//to the bottom right of the center button
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++)
			{
				//Using a try-catch so that an arrayIndexOutOfBounds doesn't break everything
				try{
					if(buttons[indexI+i][indexJ+j].getButtonState() == true){
						AliveCells++;
						//JOptionPane.showMessageDialog(null, "Button state true");
					}
				}catch(Exception e){
				//If we get that exception, just ignore it.	
				}
			}
		}
		return AliveCells;
	}
	
}//End of class
