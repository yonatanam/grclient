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
import gui.EditReviewGUI;
import gui.MainWindowGUI;
import gui.ReadWorkerGUI;
import models.Envelope;;

public class EditReviewController extends AbstractController {

	private EditReviewGUI editReviewGUI;
	private EditReviewController editReviewController;
	private int reviewID;
	private PendingReviewsController prc;




	public EditReviewController(EditReviewGUI erg, int reviewID, PendingReviewsController prc) {

		this.editReviewGUI =erg;
		this.reviewID = reviewID;
		this.prc=prc;
		editReviewController = this;  //IMPORTANT
		editReviewGUI.addButtonBackFromEditReviewActionListener(new BackFromEditReviewListener());
		editReviewGUI.addButtonSubmitEditReviewActionListener(new SubmitEditListener());
		editReviewGUI.addWindowListener(new CustomWindowListener());

	}



	class BackFromEditReviewListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			editReviewGUI.dispose();


		}
	}

	class SubmitEditListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			Map<String,Object> params = new HashMap<String,Object>();
			Envelope en = new Envelope(params);
			if (editReviewGUI.getTextArea().getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Review text is empty!","Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			params.put("reviewid", getReviewID());
			params.put("reviewcontent", editReviewGUI.getTextArea().getText());
			params.put("msg", "editReview");
			App.client.setCurrentController(geteditReviewController());
			sendToServer(en);
			params.put("msg", "getPendingReviews");
			App.client.setCurrentController(prc);
			sendToServer(en);
			editReviewGUI.dispose();
		}
	}




	/*getters and setters*/
	public EditReviewController geteditReviewController() {
		return editReviewController;
	}

	public void seteditReviewController(EditReviewController editReviewController) {
		this.editReviewController = editReviewController;
	}

	public int getReviewID() {
		return reviewID;
	}


	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	/*end getters and setters*/


}

