package controllers;


import java.awt.image.BufferedImage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import client.Client;
//import client.CliMessage;
//import client.IObserve;
import client.App;


/**
 * Abstract class for each controller, controllers use it's method to connect the server.
 *
 */
public abstract class AbstractController 
{

		/** The model. */
		protected Object theModel;

		/**
		 * Instantiates a new abstract controller.
		 * @param theModel the the model
		 * 
		 */
		public AbstractController(){}
		
		public AbstractController(Object theModel) 
		{
			this.theModel = theModel;
		
		}
		
		/**
		 * Send to server with String
		 *
		 * @param request the request
		 */
		
		
		protected void sendToServer(String request)
		{
			try 
			{
				 App.client.sendToServer(request);
			} 
			catch (IOException e)
			{
				JOptionPane.showMessageDialog(null,"Error: Server comunication problem","Commuinication Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		
	/**
	 * Send to server with Object	
	 * @param request
	 */
		
		
		protected void sendToServer(Object request)
		{
			System.out.println("SendToServer");

			try 
			{
				
				 App.client.sendToServer(request);
			} 
			catch (IOException e)
			{
				JOptionPane.showMessageDialog(null,"Error: Server comunication problem","Commuinication Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		
		/**
		 * Method to handle the answer of the db.
		 * Controller class usually needed to overwrite this method as own reqeusts.
		 * @param message Answer of the db as Object.
		 */
		 public void handleDBResult(Object message) {
			 
			 //
		 }
		 
		 public void UpdateDB(){
			 //
		 }
		
		
			
		
	}
