package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.Color;

public class WriteWorkerGUI extends JFrame {
	private JButton BackButton;
	private JButton UpdateButton;
	private JTextField WorkerID;
	private JTextField Dep;
	private JTextField nullField;
	




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
		
		JLabel lblReadFromWorker = new JLabel("Write To Worker Database");
		lblReadFromWorker.setForeground(Color.WHITE);
		lblReadFromWorker.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReadFromWorker.setBounds(39, 11, 271, 30);
		getContentPane().add(lblReadFromWorker);
		
	
		nullField = new JTextField();
		nullField.setOpaque(false);     
		getContentPane().add(nullField);
		
		//Dep field JText
		Dep = new JTextField()
		{
		    @Override public void setBorder(Border border) {   //Disable field's border
		        // No!
		    }
		};
		
		Dep.setForeground(Color.WHITE);
		Dep.setFont(new Font("Arial", Font.PLAIN, 13));
		Dep.setOpaque(false);       //Make Field transparent 
		Dep.setText("Department");
		Dep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Dep.setText("");                //Clear "Worker ID String" on focus
			}
		});
		
		Dep.setToolTipText("");
		Dep.setColumns(10);
		Dep.setBounds(269, 200, 224, 39);
		getContentPane().add(Dep);
		//END DEP
		
		
		
		//Username and Password Lables		
		JLabel WorkerField = new JLabel();
		WorkerField.setBounds(266, 200, 229, 40);
		Image img2 = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		WorkerField.setIcon(new ImageIcon(img2));
		getContentPane().add(WorkerField);
		//Worker Field END
		
		//Department Field
		JLabel DepField = new JLabel();
		DepField.setBounds(266, 239, 229, 40);
		DepField.setIcon(new ImageIcon(img2));
		getContentPane().add(DepField);
		//Department Field END
		

		
		//Username field JText
		WorkerID = new JTextField()
		{
		    @Override public void setBorder(Border border) {   //Disable field's border
		        // No!
		    }
		};
		
		WorkerID.setForeground(Color.WHITE);
		WorkerID.setFont(new Font("Arial", Font.PLAIN, 13));
		WorkerID.setOpaque(false);       //Make Field transparent 
		WorkerID.setText("Worker ID");
		WorkerID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				WorkerID.setText("");                //Clear "Worker ID String" on focus
			}
		});
		
		WorkerID.setToolTipText("");
		WorkerID.setColumns(10);
		WorkerID.setBounds(269, 239, 224, 40);
		getContentPane().add(WorkerID);
		//WorkerID field JText END
		
	
		
		//Update button
		UpdateButton = new JButton("Update");
		UpdateButton.setFont(new Font("Arial", Font.BOLD, 15));
		Image imgLogin = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		UpdateButton.setIcon(new ImageIcon(imgLogin));
		UpdateButton.setBounds(322, 292, 112, 30);
		UpdateButton.setOpaque(false);
		UpdateButton.setContentAreaFilled(false);
		UpdateButton.setBorderPainted(false);
		UpdateButton.setHorizontalTextPosition(JButton.CENTER);
		UpdateButton.setVerticalTextPosition(JButton.CENTER);
		UpdateButton.setForeground(Color.WHITE);
		getContentPane().add(UpdateButton);
		//Update button END
		
		
		//BackButton button
		BackButton = new JButton("Back");
		BackButton.setFont(new Font("Arial", Font.BOLD, 15));
		BackButton.setIcon(new ImageIcon(imgLogin));
		BackButton.setBounds(28, 492, 125, 30);
		BackButton.setOpaque(false);
		BackButton.setContentAreaFilled(false);
		BackButton.setBorderPainted(false);
		BackButton.setHorizontalTextPosition(JButton.CENTER);
		BackButton.setVerticalTextPosition(JButton.CENTER);
		BackButton.setForeground(Color.WHITE);
		getContentPane().add(BackButton);
				//BackButton button END

		setLocationRelativeTo(null);
		setVisible(true);
		
		//Background
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		Image img1 = new ImageIcon(this.getClass().getResource("/bgWithOutLogo.png")).getImage();
		bg.setIcon(new ImageIcon(img1));
		getContentPane().add(bg);
		//BackGround END
	}


	//Action Listeners
	public void addButtonUpdateWorkerActionListener(ActionListener e)
	{
		UpdateButton.addActionListener(e);
	}
	public void addButtonBackFromWriteToWorkerActionListener(ActionListener e)
	{
		BackButton.addActionListener(e);
	}
	
	
	
	
	//Getters and Setters
	public JTextField getWidTextField() {
		return WorkerID;
	}

	public void setWidTextField(JTextField widTextField) {
		this.WorkerID = widTextField;
	}

	public JTextField getDepTextField() {
		return Dep;
	}

	public void setDepTextField(JTextField depTextField) {
		this.Dep = depTextField;
	}
}
