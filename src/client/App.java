package client;
import gui.ClientGUI;
import models.ClientModel;
import controllers.ClientController;


public class App {
	public static Client client;
	public static void main(String[] args) {
		ClientGUI clientGUI = new ClientGUI(); //show initial config screen
		ClientModel clientModel = new ClientModel();
        ClientController clientController = new ClientController(clientGUI,clientModel);
	}

}
