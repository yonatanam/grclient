package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import client.App;
import gui.ShoppingCartGUI;
import gui.MainWindowGUI;
import gui.SearchGUI;
import models.Book;
import models.Envelope;

public class ShoppingCartController extends AbstractController
{
	private ShoppingCartGUI bpGUI;
	private JFileChooser jfcDir;
	private Vector<String> booksIDs = new Vector<String>();
	private String searchStr;

	public ShoppingCartController(ShoppingCartGUI bp , String searchStr)
	{
		App.client.setCurrentController(this);	
		bpGUI = bp;
		this.searchStr = searchStr;
		bpGUI.addBtnBrowseActionListener(new BrowseHandler());
		bpGUI.addBtnBackActionListener(new BackHandler());
		bpGUI.addBtnPayActionListener(new PayHandler());
		bpGUI.addWindowListener(new CustomWindowListener());
		populateShoppingTable();
		jfcDir = new JFileChooser();
		jfcDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}

	
	public void populateShoppingTable()
	{	
		float sum=0;
		String col[] = {"Book's Name" , "Price"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for (Book b :App.client.getCurrentUser().getCart())
		{
			Object [] temp = {b.getBooktitle(), b.getPrice()};
			sum += b.getPrice();
			tableModel.addRow(temp);
		}
		int numBooks = tableModel.getRowCount();
		bpGUI.getLblTotal().setText("Total "+ numBooks + " books");
		bpGUI.getLblSumResult().setText("Total price: "+sum); 
		bpGUI.getJtBooksTable().setModel(tableModel);
	}
	private class BackHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{			
			bpGUI.dispose();
			//If searchStr is null return back to main window
			if (searchStr == null)
				new MainWindowController(new MainWindowGUI());
				
			else
				new SearchController(new SearchGUI(), searchStr);
		}
	}

	private class BrowseHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{			
			int returnVal = jfcDir.showOpenDialog(bpGUI);
			if (returnVal == JFileChooser.APPROVE_OPTION) 
			{
				bpGUI.setJtDir(jfcDir.getSelectedFile().getAbsolutePath() + "\\Books.zip");
				bpGUI.enableBtnPay();
				/*
				File file = new File(path);
				boolean isDirectory = file.isDirectory();
				 */

			}
		}
	}

	private class PayHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{			
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("msg", "DownloadBooks");
			params.put("username", App.client.getCurrentUser().getUserName());
			params.put("books", App.client.getCurrentUser().getCart());
			Envelope envelope = new Envelope(params);
			sendToServer(envelope);
			bpGUI.dispose();
			MainWindowGUI mwGUI = new MainWindowGUI();
			new MainWindowController(mwGUI);
			JOptionPane.showMessageDialog(mwGUI,"Downloding files.\nYou will be notified when your files will be ready to use.","Confirmation", JOptionPane.NO_OPTION);
		}
	}

	@Override
	public void handleDBResult(Object message)
	{
		Envelope en = (Envelope)message;
		String msg = (String) en.getParams().get("msg");

		switch(msg)
		{
		case "DownloadFailed":
			JOptionPane.showMessageDialog(null,"Download faild, please contact the manager!","Error", JOptionPane.ERROR_MESSAGE); 
			break;
		case "DownloadApproved":
			System.out.println("Downloading");			
			//Clear shopping cart
			App.client.getCurrentUser().getCart().clear();
			Vector<String> filesNames = (Vector<String>) en.getParams().get("filesNames");
			Vector<byte[]> bytesOfFiles = (Vector<byte[]>)en.getParams().get("bytesOfFiles");

			FileOutputStream fos = null;
			ZipOutputStream zipOut = null;

			String userDir = bpGUI.getDirecotry();

			try 
			{
				fos = new FileOutputStream(userDir); 
				zipOut = new ZipOutputStream(new BufferedOutputStream(fos));

				for(int i = 0; i < filesNames.size(); i++)
				{
					ZipEntry ze = new ZipEntry(filesNames.get(i).toString());
					zipOut.putNextEntry(ze);
					zipOut.write(bytesOfFiles.get(i));
					zipOut.flush();
				}
				zipOut.close();
			} 
			catch (FileNotFoundException e) {e.printStackTrace();} 
			catch (IOException e) {e.printStackTrace();} 
			finally
			{
				try
				{
					if(fos != null) fos.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Download faild, please contact the manager!","Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
			JOptionPane.showMessageDialog(null,"Download is finished.\nEnjoy your books.","Confirmation", JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}	
}
