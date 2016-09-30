import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.TextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class StartWindow extends JFrame {

	private final JPanel contentPanel = new JPanel();
	public boolean clickState = false;
	private int width;
	private int length;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		public void run(){
			try{
			StartWindow dialog = new StartWindow();
			dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
			dialog.setVisible(true);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	});
		
}
	
	/**
	 * Create the dialog.
	 */
	public StartWindow() {
		
		//Set up graphical elements. Auto-Generated code from SwingDesigner
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblWhatSizeShould = new JLabel("What size should the board be?");
			lblWhatSizeShould.setBounds(36, 13, 306, 22);
			lblWhatSizeShould.setFont(new Font("Tahoma", Font.BOLD, 18));
			contentPanel.add(lblWhatSizeShould);
		}
		
		JLabel lblLength = new JLabel("Length:");
		lblLength.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLength.setBounds(36, 96, 90, 39);
		contentPanel.add(lblLength);
		
		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWidth.setBounds(36, 148, 90, 39);
		contentPanel.add(lblWidth);
		
		JSpinner lengthField = new JSpinner();
		lengthField.setModel(new SpinnerNumberModel(0, 0, 50, 1));	//JSpinner model set to a maximum int of 50
		lengthField.setBounds(138, 98, 42, 39);
		contentPanel.add(lengthField);
		
		
		JSpinner widthField = new JSpinner();
		widthField.setModel(new SpinnerNumberModel(0, 0, 50, 1));	//JSpinner model set to a maximum int of 50
		widthField.setBounds(138, 148, 42, 39);
		contentPanel.add(widthField);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				//This listens for the OK Button to be clicked. If it is clicked, the StartWindow will instantiate a new
				//instance of MainWindow with the current values of the spinners passed to the MainWindow constructor
				okButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try{
							width = (int)widthField.getValue();
							length = (int)(lengthField.getValue());
							//clickState = true;	
							MainWindow window = new MainWindow(width, length);
							
						}catch(Exception e1 )
						{
							JOptionPane.showMessageDialog(null, e1);
						}
					}
				});
				buttonPane.add(okButton);
			}
		}
	}
}