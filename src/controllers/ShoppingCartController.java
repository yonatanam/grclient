package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import client.App;
import gui.ShoppingCartGui;
import gui.MainWindowGUI;
import models.Envelope;

public class ShoppingCartController extends AbstractController
{
	private ShoppingCartGui bpGUI;
	private JFileChooser jfcDir;
	private Vector<String> booksIDs = new Vector<String>();
	
	public ShoppingCartController(ShoppingCartGui bp /*, Vector<String> bookID, BooksPurchaseClass to go back to purchase window */)
	{
		App.client.setCurrentController(this);
		
		bpGUI = bp;
		bpGUI.addBtnBrowseActionListener(new BrowseHandler());
		bpGUI.addBtnBackActionListener(new BackHandler());
		bpGUI.addBtnPayActionListener(new PayHandler());
		
		jfcDir = new JFileChooser();
		jfcDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}
	
	private class BackHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{			
			bpGUI.dispose();
			//new MainWindowController(new MainWindowGUI());
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
			params.put("Books", booksIDs);
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
				
				break;
			case "DownloadApproved":
				System.out.println("Downloading");			
				
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
