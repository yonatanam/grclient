package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import gui.SearchGUI;
import gui.SearchType;
import gui.ShoppingCartGUI;
import gui.ReadWorkerGUI;
import models.Book;
import models.Envelope;;

public class SearchController extends AbstractController {

	private SearchGUI searchGUI;
	private SearchController searchController;
	private String searchStr;
	private Map<String,String> array;


	public SearchController(SearchGUI sg, String searchStr,SearchType t) {

		this.searchGUI =sg;
		searchController = this;  //IMPORTANT
		this.searchStr  = searchStr;
		sg.addButtonBackFromSearchGUIActionListener(new BackFromSearchGUIListener());
		sg.addButtonAddToCartActionListener(new AddToCartActionListener());
		sg.addButtonCheckoutActionListner(new CheckOutActionListener());
		
		sg.addWindowListener(new CustomWindowListener());
		fetchSearchData(t);

	}
	public SearchController(SearchGUI sg,Map<String,String> s,SearchType t) {

		this.searchGUI =sg;
		searchController = this;  //IMPORTANT
		this.array  = s;
		sg.addButtonBackFromSearchGUIActionListener(new BackFromSearchGUIListener());
		sg.addButtonAddToCartActionListener(new AddToCartActionListener());
		sg.addButtonCheckoutActionListner(new CheckOutActionListener());
		
		sg.addWindowListener(new CustomWindowListener());
		fetchSearchData(t);

	}







	class BackFromSearchGUIListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			searchGUI.dispose();
			MainWindowGUI mwg = new MainWindowGUI();
			MainWindowController mwc = new MainWindowController(mwg);

		}
	}

	class CheckOutActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			searchGUI.dispose();
			new ShoppingCartController(new ShoppingCartGUI(), searchStr);
		}
	}
	
	class AddToCartActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){

			try
			{
				String bookID = (String) searchGUI.getSearchData().getValueAt(searchGUI.getSearchData().getSelectedRow(), 0);
				String booktitle = (String) searchGUI.getSearchData().getValueAt(searchGUI.getSearchData().getSelectedRow(), 1);
				float bookprice = (float) searchGUI.getSearchData().getValueAt(searchGUI.getSearchData().getSelectedRow(), 7);;
				String bookformat = (String) searchGUI.getSearchData().getValueAt(searchGUI.getSearchData().getSelectedRow(), 6);
				Book selectedBook = new Book(booktitle,bookprice, bookformat, bookID);
				if (App.client.getCurrentUser().getCart().contains(selectedBook))
					JOptionPane.showMessageDialog(null,"Book is already in cart!","Error", JOptionPane.ERROR_MESSAGE);
				else
				{
					App.client.getCurrentUser().getCart().add(selectedBook);
					JOptionPane.showMessageDialog(null,"Book was added successfuly!");
				}
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null,"Nothing selected!","Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	

	public void fetchSearchData(SearchType t)
	{
		
		String[]	temp;	
		ArrayList<String>	many ;	
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("msg", "getSimpleSearchData");
		switch(t)
		{
		case By_Category:
			params.put("inc", 0);	
			temp=searchStr.split(";");
			many = new ArrayList<String>(Arrays.asList(temp));	
			params.put("data",many);
			params.put("searchby",t);
			break;			
		case By_Name:
			params.put("inc", 1);	
			params.put("data", searchStr);	
			params.put("searchby", t);			
			break;
		case By_Author:
			params.put("inc", 0);	
			temp=searchStr.split(";");
			many = new ArrayList<String>(Arrays.asList(temp));	
			params.put("data",many);
			params.put("searchby",t);
			break;
		case By_Synop:
			params.put("inc", 1);	
			//many = new ArrayList<String>(Arrays.asList(temp));
			params.put("data",searchStr);
			params.put("searchby",t);	
			break;
		case By_Content:
			params.put("inc", 1);	
			params.put("data",searchStr);
			params.put("searchby",t);	
			break;
		case By_Lang:
			params.put("inc", 0);	
			temp=searchStr.split(";");
			many = new ArrayList<String>(Arrays.asList(temp));	
			params.put("data",many);
			params.put("searchby",t);			
			break;
		case By_KeyWords:
			params.put("inc", 1);	
			temp=searchStr.split(";");
			many = new ArrayList<String>(Arrays.asList(temp));	
			params.put("data",many);
			params.put("searchby",t);
			break;	
		case Any_Type:
			params.put("inc", 1);				
			params.put("data",this.array);
			params.put("searchby",t);
			break;	
			
		}
		
		
		
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(getSearchController());
		sendToServer(envelope);

	}


	public void handleDBResult(Object message)
	{	
		Envelope en = (Envelope)message;
		Vector<Object> columnNames = (Vector<Object>) en.getParams().get("booksColumnNames");
		Vector<Object> data = (Vector<Object>) en.getParams().get("booksData");
		searchGUI.populateTable(columnNames, data);

	}

	/**Getters and Setters*/
	public SearchGUI getSearchGUI() {
		return searchGUI;
	}

	public void setSearchGUI(SearchGUI searchGUI) {
		this.searchGUI = searchGUI;
	}

	public SearchController getSearchController() {
		return searchController;
	}

	public void setSearchController(SearchController searchController) {
		this.searchController = searchController;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	/**End getters and setters*/
}

