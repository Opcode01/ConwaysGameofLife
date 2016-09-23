import java.util.*;

public class LifeSimulator implements Runnable {
	
	int time;
	AButton buttons[][];
	
	public LifeSimulator(int sleepTime, AButton array[][])
	{
		time = sleepTime;
		buttons = array;
	}
	
	public void run(){
		while(true){
			try {
				Simulator();
				Thread.sleep(time);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}
	
	private void Simulator(){
		
		boolean selfCheck;
		int aliveNeighbors = 0;
				
			for(int i = 0; i < 25; i++ )
			{
				for(int j = 0; j < 25; j++)
				{
					//Check the state of this AButton
					selfCheck = buttons[i][j].getButtonState();
					if(selfCheck){
						//If this cell is alive check for the neighbors
						aliveNeighbors = checkButtons(i, j) - 1;
						System.out.println(aliveNeighbors);
						if(aliveNeighbors < 2)
							buttons[i][j].setButtonState(false);
						else if(aliveNeighbors > 3)
							buttons[i][j].setButtonState(false);
						
					}	
					if(!selfCheck){
						//If the cell is dead, check for at least 3 active neighbors
						aliveNeighbors = checkButtons(i,j);
						System.out.println(aliveNeighbors);
						if(aliveNeighbors == 3)
							buttons[i][j].setButtonState(true);
					}	
				}
			}
	}

		private int checkButtons(int indexI, int indexJ){
			
			int AliveCells = 0;
			for(int i = -1; i < 2; i++){
				for(int j = -1; j < 2; j++)
				{
					try{
						if(buttons[indexI+i][indexJ+j].getButtonState() == true){
							AliveCells++;
							//JOptionPane.showMessageDialog(null, "Button state true");
						}
					}catch(Exception e){
						//JOptionPane.showMessageDialog(null, e);
					}
				}
			}
			return AliveCells;
		}
}
