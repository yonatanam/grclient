package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import gui.LoginGUI;
import gui.MainWindowGUI;
import gui.ClientGUI;
import models.LoginModel;
import models.ClientModel;
import models.LoginModel;
import client.Client;
import client.App;
import client.Client;
import client.App;;

/**
 * This Class is a Client Controller which creates the Client instance and checks the port and host to server
 *
 */
public class ClientController extends  AbstractController {

	private ClientGUI clientGUI;
	private ClientModel clientModel;

	public ClientController(ClientGUI cGUI,ClientModel cModel)
	{
		this.clientGUI = cGUI;
		this.clientModel = cModel;
		cGUI.addOKActionListener(new OKListener());
		cGUI.addCancelActionListener(new CancelListener() );

	}

	class OKListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			try 
			{
				//TODO Check connection to DB here
				String host = new String (clientGUI.getHost());
				int port = clientGUI.getPort();
				clientModel.setHost(host);
				clientModel.setPort(port);	
				System.out.println("Attempting to connect to host "+host+" at port "+port);

				App.client = new Client(host,port); 
				clientGUI.dispose(); //remove the current window to display login window
				
				//Check connection to server
				if (App.client.isConnected())
				{
					//create a new login controller and gui
					LoginGUI loginGUI = new LoginGUI();
					LoginModel loginModel = new LoginModel();
					LoginController loginController = new LoginController(loginGUI,loginModel);

				}
				else
				{
					clientGUI.displayWarnningMessage("Faild to connect. check IP and port!");
					clientGUI.clearFields();
				}
			}

			catch (NumberFormatException e) 
			{
				clientGUI.displayWarnningMessage("Faild to connect. check IP and port!");
				clientGUI.clearFields();
			} 
			catch (IOException e) 
			{
				clientGUI.displayWarnningMessage("Connection problem. check IP and Port. "+ e);

			}
		}
	}
	class CancelListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			clientGUI.dispose();
			//TODO Add clean up functions here if needed
		}
	}

}
