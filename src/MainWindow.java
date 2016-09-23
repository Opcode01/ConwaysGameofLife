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

	ImageIcon play = new ImageIcon(this.getClass().getResource("Play Button.png"));
	ImageIcon pause = new ImageIcon(this.getClass().getResource("Pause Button.png"));
	
	AButton buttons[][] = new AButton[25][25];
	AButton PlayPauseButton = new AButton(pause, play);
	
	Thread Brain = new Thread(new LifeSimulator(1000, buttons));
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);		
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Conway's Game of Life"); 
		//frame.setResizable(false);
		
		for(int i = 0; i < 25; i++){
			for(int j = 0; j < 25; j++){
				buttons[i][j] = new AButton();
				panel.add(buttons[i][j]);
			}
		}
		panel.setLayout(new GridLayout(25, 25));
		frame.getContentPane().add(panel);
		
		JSeparator separator = new JSeparator();
		frame.getContentPane().add(separator, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		PlayPauseButton.setButtonState(true);
		panel_1.add(PlayPauseButton);

		frame.setVisible(true);
		
		Brain.start();
		
	}
	
	public void getPlayPauseState()
	{
		if(PlayPauseButton.getButtonState() == true)
		{
			try{
				Brain.notify();
			}
			catch(Exception e){
			}
		}
		else if(PlayPauseButton.getButtonState() == false)
		{
			try {
				Brain.wait();
			} catch (InterruptedException e) {
				Brain.notify();
			}
		}
	}
	
}//End of class