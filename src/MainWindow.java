import java.awt.EventQueue;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.Font;

public class MainWindow{

	private JFrame frame;
	public JPanel panel = new JPanel();
	
	public static int rows;
	public static int columns;
	
	ImageIcon play = new ImageIcon(this.getClass().getResource("Play Button.png"));
	ImageIcon pause = new ImageIcon(this.getClass().getResource("Pause Button.png"));
	
	AButton PlayPauseButton = new AButton(pause, play);

	/**
	 * Create the application.
	 */
	public MainWindow(int par1, int par2) {
		rows = par1;
		columns = par2;
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Conway's Game of Life"); 
		
		//This is the grid that everything happens on.
		AButton buttons[][] = new AButton[rows][columns];
				
		//Initialize all the buttons. Default 25x25 grid
			//TO-DO: Allow user to resize grid to personal liking
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				buttons[i][j] = new AButton();
				panel.add(buttons[i][j]);
			}
		}
		
		//Setting up graphical elements...
		panel.setLayout(new GridLayout(rows, columns));
		frame.getContentPane().add(panel);
		
		JSeparator separator = new JSeparator();
		frame.getContentPane().add(separator, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		PlayPauseButton.setButtonState(true);
		panel_1.add(PlayPauseButton);

		frame.setVisible(true);
		
		//Initialize the ThreadHandler to run once every 100 milliseconds
		Timer Update = new Timer();
		ThreadHandler t = new ThreadHandler(PlayPauseButton, buttons);
		Update.schedule(t, 1, 100);
		
	}
}//End of class