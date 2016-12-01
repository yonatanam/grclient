package gui;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ClientGUI extends JFrame{

	/**
	 * The Client Login window (Host:Port)
	 */
	private static final long serialVersionUID = 1L;


	private JLabel LabelPort = null;
	private JLabel LabelHost = null;
	private JTextField TextHost = null;
	private JTextField TextPort = null;
	private JButton ButtonOk = null;
	private JButton ButtonCancel = null;

	public ClientGUI() {
		getContentPane().setLayout(null);

		LabelPort = new JLabel("Port");
		LabelPort.setBounds(10, 40, 46, 14);
		getContentPane().add(LabelPort);

		LabelHost = new JLabel("Host");
		LabelHost.setBounds(10, 11, 46, 14);
		getContentPane().add(LabelHost);

		getContentPane().add(getTextHost());
		getContentPane().add(getTextPort());

		ButtonOk = new JButton("OK");
		ButtonOk.setBounds(77, 79, 89, 23);
		getContentPane().add(ButtonOk);

		ButtonCancel = new JButton("Cancel");
		ButtonCancel.setBounds(194, 79, 89, 23);
		getContentPane().add(ButtonCancel);

		this.setTitle("Good Reading - Initial Configuration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500, 200, 358, 155);
		this.setVisible(true);
		this.setResizable(false);
	}

	private JTextField getTextHost(){
		if(TextHost == null){
			TextHost = new JTextField();
			TextHost.setBounds(51, 9, 264, 20);
			TextHost.setColumns(10);
			TextHost.setText("localhost");
		}
		return TextHost;
	}

	private JTextField getTextPort(){
		if(TextPort == null){
			TextPort = new JTextField();
			TextPort.setBounds(51, 37, 264, 20);
			TextPort.setColumns(10);
			TextPort.setText("5555");
		}
		return TextPort;
	}

	public void clearFields(){
		TextHost.setText("");
		TextPort.setText("");
	}

	public String getHost(){
		return TextHost.getText();
	}

	public int getPort(){
		if(TextPort.getText().equals(""))
			return 0;
		return Integer.parseInt(TextPort.getText());
	}

	public void addOKActionListener(ActionListener listener){
		ButtonOk.addActionListener(listener);
	}

	public void addCancelActionListener(ActionListener listener){
		ButtonCancel.addActionListener(listener);
	}

	public void displayWarnningMessage(String msg)
	{
		JOptionPane.showMessageDialog(this, msg);
	}

}
