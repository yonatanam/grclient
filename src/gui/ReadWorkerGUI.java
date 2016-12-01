package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;

public class ReadWorkerGUI {

	private JFrame frame;
	private JButton btnRead;
	private JButton btnBack;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReadWorkerGUI window = new ReadWorkerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public ReadWorkerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblReadFromWorker = new JLabel("Read from Worker Database");
		lblReadFromWorker.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReadFromWorker.setBounds(39, 11, 271, 30);
		frame.getContentPane().add(lblReadFromWorker);
		
		btnRead = new JButton("Read");
		btnRead.setBounds(309, 19, 89, 23);
		frame.getContentPane().add(btnRead);
		
		JList list = new JList();
		list.setBounds(58, 118, 659, 314);
		frame.getContentPane().add(list);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(28, 499, 89, 23);
		frame.getContentPane().add(btnBack);
	}
	
	public void addButtonReadFromWorkerActionListener(ActionListener e)
	{
		btnRead.addActionListener(e);
	}
	public void addButtonBackFromReadFromWorkerActionListener(ActionListener e)
	{
		btnBack.addActionListener(e);
	}
}
