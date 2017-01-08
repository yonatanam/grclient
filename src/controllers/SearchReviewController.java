package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import client.App;
import gui.MainWindowGUI;
import gui.SearchReviewGUI;
import models.Envelope;

public class SearchReviewController extends AbstractController
{
	
	SearchReviewGUI srGUI;
	public SearchReviewController(SearchReviewGUI sr)
	{
		srGUI = sr;
		srGUI.addBtnSearchActionListener(new SearchReviewHandler());
		srGUI.addJtResultsTableListSelectionListener(new RowListener());
		srGUI.addBtnBackActionListener(new BackHandler());
		srGUI.addWindowListener(new CustomWindowListener());
		
	}

	private SearchReviewController getSearchReviewController()
	{
		return this;
	}
	
	private class BackHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{			
			srGUI.dispose();
			new MainWindowController(new MainWindowGUI());
		}
	}
	
	private class SearchReviewHandler implements ActionListener
	{		
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("SearchReviewHandler");
			HashMap<String, Object> params = new HashMap<String,Object>();
			Envelope envelope = new Envelope(params);
			params.put("searchText", srGUI.getSearchFieldText());			
			params.put("searchType", srGUI.getSearhType());
			params.put("msg", "SearchReview");
			App.client.setCurrentController(getSearchReviewController());
			sendToServer(envelope);				
		}	
	}
	
    private class RowListener implements ListSelectionListener 
    {    	
        public void valueChanged(ListSelectionEvent e) 
        {
            if (e.getValueIsAdjusting()) 
                return;    
            int selectedRow = srGUI.getSelectedRow();
            if(selectedRow != -1)
            {
            	JTextArea jtaReviewContent = srGUI.getJtaReviewContent();
	            String reviewContent = srGUI.getReviewContentFromJTable(selectedRow);
	            jtaReviewContent.setText(reviewContent);
	            jtaReviewContent.setCaretPosition(0);
	            srGUI.getJspReviewContent().setVisible(true);
            }
        	
        }
        
        /*
	      	int[] columns = jtable.getSelectedColumns();
			TableModel model = jtable.getModel();
			int rowcount = model.getRowCount();
			String[][] output = new String[columns.length][rowcount];
			for (int i = 0; i < columns.length; i++)
			    for (int row = 0; row < rowcount; row++){
			        int column = jtable.convertColumnIndexToModel(columns[i]);
			        output[i][row] = model.getValueAt(row, column).toString();
			    }
         */
    }	
	public void handleDBResult(Object message)
	{	
		Envelope en = (Envelope)message;
		String msg = (String) en.getParams().get("msg");
		if(msg.equals("SearchReviewResultsEmpty"))
			srGUI.emptyResults();
		else
		{
	        Vector<Object> columnNames = (Vector<Object>) en.getParams().get("columnNames");
	        Vector<Object> data = (Vector<Object>) en.getParams().get("data");
	        srGUI.generateResultsTable(columnNames, data);		
		}
	}
}
