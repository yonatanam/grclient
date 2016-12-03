package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSet;

import client.App;
import gui.MainWindowGUI;
import gui.WriteWorkerGUI;
import models.Envelope;;

public class WriteWorkerController extends AbstractController {

	private WriteWorkerGUI writeWorkerGUI;
	private WriteWorkerController writeWorkerController;


	public WriteWorkerController(WriteWorkerGUI wwg) {

		this.writeWorkerGUI =wwg;
		writeWorkerController = this;  //IMPORTANT
		wwg.addButtonUpdateWorkerActionListener(new UpdateWorkerListener());
		wwg.addButtonBackFromWriteToWorkerActionListener(new BackFromReadFromWorkerListener());
	}


	class UpdateWorkerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			
			String wid = writeWorkerGUI.getWidTextField().getText();
			String dep = writeWorkerGUI.getDepTextField().getText();
			
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("msg", "checkWorkerData");
			params.put("wid", wid);
			params.put("dep", dep);
			Envelope envelope = new Envelope(params);
			App.client.setCurrentController(getWriteWorkerController());
			sendToServer(envelope);

		}
	}

	class BackFromReadFromWorkerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			writeWorkerGUI.dispose();
			MainWindowGUI mwg = new MainWindowGUI();
			MainWindowController mwc = new MainWindowController(mwg);
		}
	}

	public void handleDBResult(Object message)
	{	
		Envelope en = (Envelope)message;
		
	}
	
	public WriteWorkerController getWriteWorkerController() {
		return writeWorkerController;
	}

	public void setWriteWorkerController(WriteWorkerController writeWorkerController) {
		this.writeWorkerController = writeWorkerController;
	}
	
}

