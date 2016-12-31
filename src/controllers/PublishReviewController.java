package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
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
import models.Envelope;;

public class PublishReviewController extends AbstractController {

	private PublishReviewGUI publishReviewGUI;
	private PublishReviewController publishReviewController;




	public PublishReviewController(PublishReviewGUI prg) {

		this.publishReviewGUI =prg;
		publishReviewController = this;  //IMPORTANT
		populateBooksRead();
		prg.addButtonBackFromReadFromWorkerActionListener(new BackFromPublishReviewListener());
		prg.addButtonSubmitPublishReviewActionListener(new SubmitPublishReviewListener());

	}



	class BackFromPublishReviewListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			publishReviewGUI.dispose();
			MainWindowGUI mwg = new MainWindowGUI();
			MainWindowController mwc = new MainWindowController(mwg);


		}
	}

	class SubmitPublishReviewListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			Map<String,Object> params = new HashMap<String,Object>();
			Envelope en = new Envelope(params);
			if (publishReviewGUI.getTextArea().getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Review text is empty!","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (publishReviewGUI.getChckbxNewCheckBox().isSelected())
			{	
				params.put("msg", "PublishReview");
				params.put("booktitle", publishReviewGUI.getChooseBookComboBox().getSelectedItem().toString());
				params.put("username", App.client.getCurrentUser().getUserName());
				params.put("content", publishReviewGUI.getTextArea().getText());
				params.put("keywords", publishReviewGUI.getKeywordTextField().getText());
				App.client.setCurrentController(getPublishReviewController());
				sendToServer(en);
			}
			else
				JOptionPane.showMessageDialog(null,"Review must be signed!","Error", JOptionPane.ERROR_MESSAGE);
		}
	}


	public void handleDBResult(Object message)
	{	
		Envelope en = (Envelope)message;
		if (en.getParams().get("msg").equals("GetBooksRead"))
		{
			ArrayList<String> bookTitles = (ArrayList<String>)en.getParams().get("booktitles");
			for (String bookTitle : bookTitles)
				publishReviewGUI.getChooseBookComboBox().addItem(bookTitle);
		}
		else
			if (en.getParams().get("msg").equals("PublishReviewOK"))
			{
				JOptionPane.showMessageDialog(null,"Review was published successfuly!");

			}
			else
				JOptionPane.showMessageDialog(null,"Review already exists!","Error", JOptionPane.ERROR_MESSAGE);

	}

	public void populateBooksRead()
	{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("msg", "getBooksRead");
		params.put("username", App.client.getCurrentUser().getUserName());
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(getPublishReviewController());
		sendToServer(envelope);

	}
	/*getters and setters*/
	public PublishReviewController getPublishReviewController() {
		return publishReviewController;
	}

	public void setPublishReviewController(PublishReviewController publishReviewController) {
		this.publishReviewController = publishReviewController;
	}
	/*end getters and setters*/


}

