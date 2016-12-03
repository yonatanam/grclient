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

public class ReadWorkerGUI extends JFrame {

	private JButton btnRead;
	private JButton btnBack;
	private JTable workerData;
	private JScrollPane scrollPane;
	


	public ReadWorkerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblReadFromWorker = new JLabel("Read from Worker Database");
		lblReadFromWorker.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReadFromWorker.setBounds(39, 11, 271, 30);
		getContentPane().add(lblReadFromWorker);
		
		btnRead = new JButton("Read");
		btnRead.setBounds(309, 19, 89, 23);
		getContentPane().add(btnRead);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(28, 499, 89, 23);
		getContentPane().add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 63, 713, 425);
		getContentPane().add(scrollPane);
		
		workerData = new JTable();
		scrollPane.setViewportView(workerData);
		workerData.setDefaultEditor(Object.class, null); //prevents from editing the fields but allows row selection

		setLocationRelativeTo(null);
		setVisible(true);
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
	
	public JTable getWorkerData() {
		return workerData;
	}

	public void setWorkerData(JTable data) {
		this.workerData = data;
	}
	
	public void populateTable(Vector<Object> columnNames, Vector<Object> data)
	{
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		workerData.setModel(model);
	}
	

}
