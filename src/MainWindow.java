import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.util.Timer;
import java.awt.Font;
import java.awt.FlowLayout;


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
	
	public MainWindow(){
		rows = 25;
		columns = 25;
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
			//TO-DO: Make a randomize button that sets all the buttons to an initial on or off state randomly.
		AButton buttons[][] = new AButton[rows][columns];
				
		//Initialize all the buttons. Default 25x25 grid
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
		
		JLabel lblSimulationSpeed = new JLabel("Simulation Speed:");
		lblSimulationSpeed.setHorizontalAlignment(SwingConstants.LEFT);
		lblSimulationSpeed.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(lblSimulationSpeed);
		
		JSlider slider = new JSlider();
		slider.setValue(100);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMaximum(1000);
		slider.setMinimum(50);
		panel_1.add(slider);
		
		PlayPauseButton.setButtonState(true);
		panel_1.add(PlayPauseButton);

		frame.setVisible(true);
		
		//Initialize the ThreadHandler to run at a user defined interval
		ThreadHandler t = new ThreadHandler(PlayPauseButton, buttons, slider);
		Timer Update = new Timer();
		Update.schedule(t, 1, slider.getValue());
		
	}
}//End of class