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
import gui.PublishReviewGUI;
import gui.ReadWorkerGUI;
import gui.TemplateGUI;
import models.Envelope;;

public class TemplateController extends AbstractController {

	private TemplateGUI publishReviewGUI;
	private TemplateController templateController;






	public TemplateController(TemplateGUI tg) {

		this.publishReviewGUI =tg;
		templateController = this;  //IMPORTANT
		tg.addButtonBackFromReadFromWorkerActionListener(new BackFromReadFromWorkerListener());


	}


	class ReadDataFromWorkerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("msg", "getWorkerData");
			Envelope envelope = new Envelope(params);
			App.client.setCurrentController(getTemplateController());
			sendToServer(envelope);

		}
	}

	class BackFromReadFromWorkerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			publishReviewGUI.dispose();
			MainWindowGUI mwg = new MainWindowGUI();
			MainWindowController mwc = new MainWindowController(mwg);

			
		}
	}

	public void handleDBResult(Object message)
	{	
		Envelope en = (Envelope)message;

		
	}
	
	public TemplateController getTemplateController() {
		return templateController;
	}

	public void setTemplateController(TemplateController templateController) {
		this.templateController = templateController;
	}

}

