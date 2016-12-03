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
	private JList workerData;
	


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
		
		JList data = new JList();
		data.setBounds(58, 118, 659, 314);
		
		frame.getContentPane().add(data);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(28, 499, 89, 23);
		frame.getContentPane().add(btnBack);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
//Action Listeners
	public void addButtonReadFromWorkerActionListener(ActionListener e)
	{
		btnRead.addActionListener(e);
	}
	public void addButtonBackFromReadFromWorkerActionListener(ActionListener e)
	{
		btnBack.addActionListener(e);
	}
	
//Setters and Getters
	
	public JList getWorkerData() {
		return workerData;
	}

	public void setWorkerData(JList data) {
		this.workerData = data;
	}
}
