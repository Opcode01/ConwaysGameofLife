/*
This class handles all the logic for the game of life simulator. 
Its the 'Brain' of the game of life. 
Designed to run in a thread
*/

public class LifeSimulator implements Runnable {
	
	int rows = MainWindow.rows;
	int cols = MainWindow.columns;
	int time;
	AButton buttons[][] = new AButton[MainWindow.rows][MainWindow.columns];
	AButton MainButtons[][];
	
	public LifeSimulator(AButton array[][])
	{
		//Get what we need from the ThreadHandler, which gets it from MainWindow
		MainButtons = array;
	}
	
	public void run(){
			try {
				initCopyButtons();
				copyBoard(MainButtons);
				Simulator(MainButtons);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/*
	 * BUG FIXED 9/28/16: Created a second dummy array of buttons. The system calculates using the buttons
	 * array, but all changes are made to the main buttons array that is passed in from the system. (Also
	 * known as MainButtons locally).
	 */
	private void initCopyButtons(){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				buttons[i][j] = new AButton();
			}
		}
	}
	
	private void copyBoard(AButton array[][])
	{
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				buttons[i][j].setButtonState(array[i][j].getButtonState());
			}
		}
	}
	
	//The really complicated method that runs the logic
	private void Simulator(AButton array[][] ){
		
		boolean selfCheck;	//State of the button we are checking	
		int aliveNeighbors = 0;	//Value we get from the checkButtons function
		
			for(int i = 0; i < rows; i++ )
			{
				for(int j = 0; j < cols; j++)
				{
					//Check the state of this AButton
					selfCheck = buttons[i][j].getButtonState();
					if(selfCheck){
						//If this cell is alive, check for the neighbors
						aliveNeighbors = checkButtons(i, j) - 1;	//We don't want to count ourself, so do checkButtons - 1
						System.out.println(aliveNeighbors);
						//Live cell with less than 2 neighbors dies
						if(aliveNeighbors < 2)
							array[i][j].setButtonState(false);
						//Live cell with more than 3 neighbors dies
						else if(aliveNeighbors > 3)
							array[i][j].setButtonState(false);
						//If live cell with 2 or 3 neighbors, do nothing
						
					}	
					if(!selfCheck){
						//If the cell is dead, check for exactly 3 active neighbors
						aliveNeighbors = checkButtons(i,j);		//This time, it doesn't matter if we count ourselves
						System.out.println(aliveNeighbors);
						if(aliveNeighbors == 3)
							array[i][j].setButtonState(true);
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
				}catch(Exception ArrayIndexOutOfBounds){
				//If we get that exception, just ignore it.	
				}
			}
		}
		return AliveCells;
	}
	
}//End of class
