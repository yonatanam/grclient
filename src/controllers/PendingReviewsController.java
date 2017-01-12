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
import controllers.AbstractController.CustomWindowListener;
import gui.EditReviewGUI;
import gui.MainWindowGUI;
import gui.PendingReviewsGUI;
import gui.ReadWorkerGUI;
import models.Envelope;;

public class PendingReviewsController extends AbstractController {

	private PendingReviewsGUI pendingReviewsGUI;
	private PendingReviewsController pendingReviewsController;




	public PendingReviewsController(PendingReviewsGUI prg) {

		this.pendingReviewsGUI =prg;
		pendingReviewsController = this;  //IMPORTANT
		prg.addButtonBackFromReadFromWorkerActionListener(new BackFromReadFromWorkerListener());
		prg.addButtonApproveReviewActionListener(new ApproveReviewActionListener());
		prg.addButtonDenyReviewActionListener(new DenyReviewActionListener());
		prg.addButtonEditReviewActionListener(new EditReviewActionListener());
		prg.addWindowListener(new CustomWindowListener());
		fetchReviewData();



	}




	class BackFromReadFromWorkerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			pendingReviewsGUI.dispose();
			MainWindowGUI mwg = new MainWindowGUI();
			MainWindowController mwc = new MainWindowController(mwg);


		}
	}

	class ApproveReviewActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			Map<String,Object> params = new HashMap<String,Object>();
			Envelope en = new Envelope(params);
			params.put("msg", "approveReview");
			params.put("reviewid", pendingReviewsGUI.getReviewsData().getValueAt(pendingReviewsGUI.getReviewsData().getSelectedRow(), 0));
			System.out.println("selected review id is "+ params.get("reviewid"));	
			sendToServer(en);
			fetchReviewData();
		}
	}
	class DenyReviewActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			Map<String,Object> params = new HashMap<String,Object>();
			Envelope en = new Envelope(params);
			params.put("msg", "denyReview");
			params.put("reviewid", pendingReviewsGUI.getReviewsData().getValueAt(pendingReviewsGUI.getReviewsData().getSelectedRow(), 0));
			System.out.println("selected review id is "+ params.get("reviewid"));	
			sendToServer(en);
			fetchReviewData();

		}
	}
	class EditReviewActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			if ( pendingReviewsGUI.getReviewsData().getSelectedRow() != -1)
			{
				String reviewText = (String)pendingReviewsGUI.getReviewsData().getValueAt(pendingReviewsGUI.getReviewsData().getSelectedRow(), 3);
				int reviewID = (Integer)pendingReviewsGUI.getReviewsData().getValueAt(pendingReviewsGUI.getReviewsData().getSelectedRow(), 0);
				EditReviewController erc = new EditReviewController(new EditReviewGUI(reviewText), reviewID, pendingReviewsController);
			}
		}
	}

	public void fetchReviewData()
	{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("msg", "getPendingReviews");
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(getPendingReviewsController());
		sendToServer(envelope);

	}

	public void handleDBResult(Object message)
	{	
		Envelope en = (Envelope)message;

		switch ((String)en.getParams().get("msg"))
		{
		case "editReviewOK":
			JOptionPane.showMessageDialog(null,"Review was edited successfuly!");
			break;
		case "editReviewNOTOK":
			JOptionPane.showMessageDialog(null,"Review editing failed!","Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
		Vector<Object> columnNames = (Vector<Object>) en.getParams().get("reviewsColumnNames");
		Vector<Object> data = (Vector<Object>) en.getParams().get("reviewsData");
		pendingReviewsGUI.populateTable(columnNames, data);

	}

	/**Getters and Setters*/
	public PendingReviewsGUI getPendingReviewsGUI() {
		return pendingReviewsGUI;
	}

	public void setPendingReviewsGUI(PendingReviewsGUI pendingReviewsGUI) {
		this.pendingReviewsGUI = pendingReviewsGUI;
	}

	public PendingReviewsController getPendingReviewsController() {
		return pendingReviewsController;
	}

	public void setPendingReviewsController(PendingReviewsController pendingReviewsController) {
		this.pendingReviewsController = pendingReviewsController;
	}
	/**End getters and setters*/
}

