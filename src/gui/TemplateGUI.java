package gui;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class TemplateGUI extends JFrame {


	private JButton BackButton;
	


	public TemplateGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		//Back button
		BackButton = new JButton("Back");
		BackButton.setFont(new Font("Arial", Font.BOLD, 15));
		BackButton.setBounds(28, 499, 106, 41);
		BackButton.setOpaque(false);
		BackButton.setContentAreaFilled(false);
		BackButton.setBorderPainted(false);
		BackButton.setHorizontalTextPosition(JButton.CENTER);
		BackButton.setVerticalTextPosition(JButton.CENTER);
		BackButton.setForeground(Color.WHITE);
		getContentPane().add(BackButton);

		setLocationRelativeTo(null);
		setVisible(true);
		
		
		//Background
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		Image img1 = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		bg.setIcon(new ImageIcon(img1));
		getContentPane().add(bg);
		//BackGround END
	}


	//Action Listeners

	public void addButtonBackFromReadFromWorkerActionListener(ActionListener e)
	{
		BackButton.addActionListener(e);
	}
	
//Setters and Getters
	

	

	

}
