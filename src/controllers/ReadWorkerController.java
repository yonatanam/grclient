package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.mysql.jdbc.ResultSet;

import client.App;
import gui.MainWindowGUI;
import gui.ReadWorkerGUI;
import models.Envelope;;

public class ReadWorkerController extends AbstractController {

	private ReadWorkerGUI readWorkerGUI;
	private ReadWorkerController readWorkerController;




	public ReadWorkerController(ReadWorkerGUI rwg) {

		this.readWorkerGUI =rwg;
		readWorkerController = this;  //IMPORTANT
		rwg.addButtonReadFromWorkerActionListener(new ReadDataFromWorkerListener());
		rwg.addButtonBackFromReadFromWorkerActionListener(new BackFromReadFromWorkerListener());


	}


	class ReadDataFromWorkerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			Map<String,Object> params = new LinkedHashMap<String,Object>();
			params.put("msg", "getWorkerData");
			Envelope envelope = new Envelope(params);
			App.client.setCurrentController(getReadWorkerController());
			sendToServer(envelope);

		}
	}

	class BackFromReadFromWorkerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			// get back to main gui	
		}
	}

	public void handleDBResult(Object message)
	{	
		System.out.println("Handling DB result in WorkerController");
		Envelope en = (Envelope)message;
		readWorkerGUI.getWorkerData().setListData((Object[]) en.getParams().get("data"));
	}
	
	public ReadWorkerController getReadWorkerController() {
		return readWorkerController;
	}

	public void setReadWorkerController(ReadWorkerController readWorkerController) {
		this.readWorkerController = readWorkerController;
	}
}

