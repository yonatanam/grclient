package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MainWindowGUI;
import gui.ReadWorkerGUI;;

public class ReadWorkerController extends AbstractController {

	private ReadWorkerGUI rwg;

	public ReadWorkerController(ReadWorkerGUI rwg) {

		this.rwg =rwg;
		rwg.addButtonReadFromWorkerActionListener(new ReadDataFromWorkerListener());
		rwg.addButtonBackFromReadFromWorkerActionListener(new BackFromReadFromWorkerListener());

	
	}
	
	
	class ReadDataFromWorkerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			
			// insert data to JList here
		}
	}
	
	class BackFromReadFromWorkerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
		// get back to main gui	
		}
	}
}

