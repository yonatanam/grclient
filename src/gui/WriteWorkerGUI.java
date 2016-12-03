package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class WriteWorkerGUI extends JFrame {
	private JButton btnBack;
	private JButton btnUpdate;
	private JTextField widTextField;
	private JTextField depTextField;
	




	public WriteWorkerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblReadFromWorker = new JLabel("Write to Worker Database");
		lblReadFromWorker.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReadFromWorker.setBounds(39, 11, 271, 30);
		getContentPane().add(lblReadFromWorker);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(28, 499, 89, 23);
		getContentPane().add(btnBack);
		
		JLabel lblWorker = new JLabel("Worker ID:");
		lblWorker.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWorker.setBounds(39, 103, 112, 23);
		getContentPane().add(lblWorker);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDepartment.setBounds(39, 137, 123, 23);
		getContentPane().add(lblDepartment);
		
		widTextField = new JTextField();
		widTextField.setBounds(161, 104, 149, 21);
		getContentPane().add(widTextField);
		widTextField.setColumns(10);
		
		depTextField = new JTextField();
		depTextField.setBounds(161, 139, 149, 21);
		getContentPane().add(depTextField);
		depTextField.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(39, 185, 89, 23);
		getContentPane().add(btnUpdate);

		setLocationRelativeTo(null);
		setVisible(true);
	}


	//Action Listeners
	public void addButtonUpdateWorkerActionListener(ActionListener e)
	{
		btnUpdate.addActionListener(e);
	}
	public void addButtonBackFromWriteToWorkerActionListener(ActionListener e)
	{
		btnBack.addActionListener(e);
	}
	
	
	
	
	//Getters and Setters
	public JTextField getWidTextField() {
		return widTextField;
	}

	public void setWidTextField(JTextField widTextField) {
		this.widTextField = widTextField;
	}

	public JTextField getDepTextField() {
		return depTextField;
	}

	public void setDepTextField(JTextField depTextField) {
		this.depTextField = depTextField;
	}
}
