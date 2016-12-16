package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ClientGUI extends JFrame{

	/**
	 * The Client Login window (Host:Port)
	 */
	private static final long serialVersionUID = 1L;


	private JLabel LabelPort = null;
	private JLabel LabelHost = null;
	private JTextField TextHost = null;
	private JTextField TextPort = null;
	private JTextField nullField = null;
	private JButton ButtonOk = null;
	private JButton ButtonCancel = null;

	public ClientGUI() {
		getContentPane().setLayout(null);
		
				nullField = new JTextField();
				nullField.setOpaque(false);     
				getContentPane().add(nullField);
		
				//Hostname label		
				JLabel HostName = new JLabel();
				HostName.setBounds(71, 9, 229, 40);
				Image img1 = new ImageIcon(this.getClass().getResource("/passSmall.png")).getImage();
				HostName.setIcon(new ImageIcon(img1));
				getContentPane().add(HostName);
				
				
				//Hostname JTextField
				TextHost = new JTextField()
				{
				    @Override public void setBorder(Border border) {   //Disable field's border
				        // No!
				    }
				};
				
				TextHost.setForeground(Color.WHITE);
				TextHost.setHorizontalAlignment(JTextField.CENTER);
				TextHost.setFont(new Font("Arial", Font.PLAIN, 13));
				TextHost.setOpaque(false);       //Make Field transparent 
				TextHost.setText("localhost");
				TextHost.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						TextHost.setText("");                //Clear "Username String" on focus
					}
				});
				
				TextHost.setToolTipText("");
				TextHost.setColumns(10);
				TextHost.setBounds(77, 20, 210, 23);
				getContentPane().add(TextHost);
				//Hostname JTextField END
				
				
				
				
				//Port label		
				JLabel Port = new JLabel();
				Port.setBounds(71, 40, 229, 40);
				Image img2 = new ImageIcon(this.getClass().getResource("/passSmall.png")).getImage();
				Port.setIcon(new ImageIcon(img2));
				getContentPane().add(Port);
				//Port label END
				
				
				
				
				//Port JTextField
				TextPort = new JTextField()
				{
				    @Override public void setBorder(Border border) {   //Disable field's border
				        // No!
				    }
				};
				
				TextPort.setForeground(Color.WHITE);
				TextPort.setHorizontalAlignment(JTextField.CENTER);
				TextPort.setFont(new Font("Arial", Font.PLAIN, 13));
				TextPort.setOpaque(false);       //Make Field transparent 
				TextPort.setText("5555");
				TextPort.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						TextPort.setText("");                //Clear "Port String" on focus
					}
				});
				
				TextPort.setToolTipText("");
				TextPort.setColumns(10);
				TextPort.setBounds(77, 50, 210, 23);
				getContentPane().add(TextPort);
				
				
				
				
		/*LabelPort = new JLabel("Port");
		LabelPort.setBounds(10, 40, 46, 14);
		getContentPane().add(LabelPort);

		LabelHost = new JLabel("localhost");
		LabelHost.setBounds(32, 83, 222, 14);
		getContentPane().add(LabelHost);
		*/

		
		//getContentPane().add(getTextPort());

		
		//Ok button		
		ButtonOk = new JButton("OK");
		ButtonOk.setBounds(71, 86, 99, 23);
		ButtonOk.setFont(new Font("Arial Black", Font.PLAIN, 13));
		Image imgOK = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		ButtonOk.setIcon(new ImageIcon(imgOK));
		ButtonOk.setOpaque(false);
		ButtonOk.setContentAreaFilled(false);
		ButtonOk.setBorderPainted(false);
		ButtonOk.setHorizontalTextPosition(JButton.CENTER);
		ButtonOk.setVerticalTextPosition(JButton.CENTER);
		ButtonOk.setForeground(Color.WHITE);
		getContentPane().add(ButtonOk);
		//Ok Button END
		
		//Cancel Button
		ButtonCancel = new JButton("Cancel");
		ButtonCancel.setBounds(198, 86, 99, 23);
		ButtonCancel.setFont(new Font("Arial Black", Font.PLAIN, 13));
		ButtonCancel.setIcon(new ImageIcon(imgOK));
		ButtonCancel.setOpaque(false);
		ButtonCancel.setContentAreaFilled(false);
		ButtonCancel.setBorderPainted(false);
		ButtonCancel.setHorizontalTextPosition(JButton.CENTER);
		ButtonCancel.setVerticalTextPosition(JButton.CENTER);
		ButtonCancel.setForeground(Color.WHITE);
		getContentPane().add(ButtonCancel);
		//Cancel Button END
		
		
		//Background Image
		JLabel ImageHere = new JLabel();
		ImageHere.setBounds(0, 0, 358, 120);
		Image img4 = new ImageIcon(this.getClass().getResource("/bgSmall.png")).getImage();
		ImageHere.setIcon(new ImageIcon(img4));
		getContentPane().add(ImageHere);
		//Background Image END

		this.setTitle("Good Reading - Initial Config");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500, 200, 358, 155);
		this.setVisible(true);
		this.setResizable(false);
		
		
	    
	}

	/*private JTextField getTextHost(){
		if(TextHost == null){
			TextHost = new JTextField();
			TextHost.setBounds(51, 9, 264, 20);
			TextHost.setColumns(10);
			TextHost.setText("localhost");
		}
		return TextHost;
	}*/

	/*private JTextField getTextPort(){
		if(TextPort == null){
			TextPort = new JTextField();
			TextPort.setBounds(51, 37, 264, 20);
			TextPort.setColumns(10);
			TextPort.setText("5555");
		}
		return TextPort;
	}*/

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
