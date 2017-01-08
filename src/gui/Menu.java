package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu  extends JPanel {
	private  SlideContainer slideContainer ;
	
	public Menu(JPanel menu) {
		setLayout(null);
		this.setOpaque(false);
		JButton menuIcon = new JButton("");
		menuIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				menuIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				menuIcon.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		//menuIcon.setBounds(130, 235, 142, 23);
		
		Image menu1 = new ImageIcon(this.getClass().getResource("/menuIcon.png")).getImage();
		menuIcon.setIcon(new ImageIcon(menu1));
		menuIcon.setOpaque(false);
		menuIcon.setContentAreaFilled(false);
		menuIcon.setBorderPainted(false);
		add(menuIcon);
		menuIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				slideContainer.Switch();				
			}
		});
		menuIcon.setBounds(5, 0, 32, 32);
		add(menuIcon);
		
		JPanel menupanel = new JPanel();
		menupanel.setOpaque(false);
		menupanel.setBounds(0, 1, 175, 556);
		menupanel.setLayout(new BorderLayout(0, 0));
		slideContainer=new SlideContainer(menu,menupanel.getBounds());
		menupanel.add(slideContainer, BorderLayout.NORTH);
		add(menupanel);
		
		
	}

}
