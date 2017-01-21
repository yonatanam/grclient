package controllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;



import client.App;
import models.Envelope;

import gui.MainWindowGUI;
import gui.SearchGUI;
import gui.SearchType;



/**
 * Main Window Controller , handles all the events in the relevant GUI.
 *
 */
public class MainWindowController extends  AbstractController {
	
	private LoginController lc;
	private MainWindowGUI mwGui;
	private MainWindowController tempL;
	private boolean status = true;
	public static final long DEFAULT_THREAD = 0;

	
	/**
	 * This is constructor of the main window, construct all the listeners and attributes.
	 * @param user
	 * @param lc
	 * @param mwGui
	 */
	public MainWindowController(MainWindowGUI mwGui) {
		this.mwGui = mwGui;
		tempL = this;		
		mwGui.addWindowListener(new CustomWindowListener());
		mwGui.advancedSearchcActionListener(new advancedSearchListener());
		mwGui.addSearchButtonActionListener(new SearchListener());
    	mwGui.addKeySearchButtonActionListener(new KeyWordsSearchListener());
		mwGui.addCategorySearchButtonActionListener(new CategorySearchListener());
		mwGui.addAuthorSearchButtonActionListener(new AuthorSearchListener());
		mwGui.addtocSearchButtonActionListener(new tocSearchListener());
		mwGui.addlangSearchButtonActionListener(new LanguageSearchListener());
		mwGui.addSynopsisSearchButtonActionListener(new SynopsisSearchListener());
		mwGui.addanySearchButtonActionListener(new anySearchListener());
	}
	class anySearchListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setwarningNull();
			String SynopsissearchStr = mwGui.getSynopsisSearchBar().getText();
			String CategorysearchStr = mwGui.getCategorySearchBar().getText();
			String langsearchStr = mwGui.getlangSearchBar().getText();
			String tocsearchStr = mwGui.gettocSearchBar().getText();
			String authorsearchStr = mwGui.getauthorSearchBar().getText();
			String keywordssearchStr = mwGui.getKeyWordsSearchBar().getText();
			boolean synopsis=(SynopsissearchStr.equals("")||SynopsissearchStr.equals("Search by book's Synopsis"));
			boolean Category=(CategorysearchStr.equals("")||CategorysearchStr.equals("Search by Category"));
			boolean language=(langsearchStr.equals("")||langsearchStr.equals("Search by book's language"));
			boolean author=(authorsearchStr.equals("")||authorsearchStr.equals("Search by author"));
			boolean toc=(tocsearchStr.equals("")||tocsearchStr.equals("Search by table of content"));
			boolean keywords=(keywordssearchStr.equals("")||keywordssearchStr.equals("Search by keyword"));
			if(synopsis&&Category&&language&&author&&toc&&keywords)				
				mwGui.setany_Warning("you need to enter at least one criteria");
			
			else{
				Map<String,String> arr = new HashMap<String,String>();
				if(!synopsis)	arr.put("synopsis",SynopsissearchStr);
				if(!Category)	arr.put("category",CategorysearchStr);
				if(!language)	arr.put("language",langsearchStr);
				if(!toc)		arr.put("toc",tocsearchStr);
				if(!author)		arr.put("author",authorsearchStr);
				if(!keywords)	arr.put("keywords",keywordssearchStr);
			mwGui.dispose();				
			SearchController sc = new SearchController(new SearchGUI(), arr,SearchType.Any_Type);
			}
		}
	}
	class SynopsisSearchListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setwarningNull();
			String searchStr = mwGui.getSynopsisSearchBar().getText();
			if(searchStr.equals("")||searchStr.equals("Search by book's Synopsis"))				
				mwGui.setsynopsis_Warning("you need to enter relevant text");
			
			else{
			mwGui.dispose();				
			SearchController sc = new SearchController(new SearchGUI(), searchStr,SearchType.By_Synop);
			}
		}
	}	
	class CategorySearchListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setwarningNull();
			String searchStr = mwGui.getCategorySearchBar().getText();
			if(searchStr.equals("")||searchStr.equals("Search by Category"))
				mwGui.setcategory_Warning("you need to enter relevant text");
			else
			{
				
				mwGui.dispose();
				SearchController sc = new SearchController(new SearchGUI(), searchStr,SearchType.By_Category);	
			}
			
		}
	}	
	class LanguageSearchListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setwarningNull();
			String searchStr = mwGui.getlangSearchBar().getText();
			if(searchStr.equals("")||searchStr.equals("Search by book's language"))
				mwGui.setlanguage_Warning("you need to enter relevant text");
		
		else{
			mwGui.dispose();
			SearchController sc = new SearchController(new SearchGUI(), searchStr,SearchType.By_Lang);			
			}
		}
	}
	class AuthorSearchListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setwarningNull();
			String searchStr = mwGui.getauthorSearchBar().getText();
			if(searchStr.equals("")||searchStr.equals("Search by author"))
				mwGui.setauthor_Warning("you need to enter relevant text");
			else
			{
			mwGui.dispose();			
			SearchController sc = new SearchController(new SearchGUI(), searchStr,SearchType.By_Author);
			}
		}
	}
	class tocSearchListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setwarningNull();
			String searchStr = mwGui.gettocSearchBar().getText();
			if(searchStr.equals("")||searchStr.equals("Search by table of content"))
				mwGui.settoc_Warning("you need to enter relevant text");
			else
			{
			mwGui.dispose();			
			SearchController sc = new SearchController(new SearchGUI(), searchStr,SearchType.By_Content);	
			}
		}
	}
		class KeyWordsSearchListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setwarningNull();
			String searchStr = mwGui.getKeyWordsSearchBar().getText();
			if(searchStr.equals("")||searchStr.equals("Search by keyword"))
				mwGui.setkeyWord_Warning("you need to enter relevant text");
			else
			{
				mwGui.dispose();
				SearchController sc = new SearchController(new SearchGUI(), searchStr,SearchType.By_KeyWords);
			}
		}
	}	
	class SearchListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			mwGui.dispose();
			String searchStr = mwGui.getSearchBar().getText();
			SearchController sc = new SearchController(new SearchGUI(), searchStr,SearchType.By_Name);

			
		}	
	}
	
	class advancedSearchListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(status)
			{
				mwGui.displayAdvancedSearchField();
				status = false;
			}
			else
			{
				mwGui.disposeAdvancedSearchField();
				status = true;
			}

			
		}	
	}//action
	private void setwarningNull()
	{
		mwGui.setauthor_Warning("");
		mwGui.settoc_Warning("");
		mwGui.setkeyWord_Warning("");
		mwGui.setsynopsis_Warning("");
		mwGui.setlanguage_Warning("");
		mwGui.setcategory_Warning("");
	}
	
}
