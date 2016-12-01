How to install WindowBuilder
===========================
Eclipse - Install new software
http://download.eclipse.org/windowbuilder/WB/release/R201506241200-1/4.5/
WindowBuilder Engine + Swing
Right click on class - > open with window builder 
======================================

Basic packages explanation:

Client package - All client source code
-App : The main client code that launches
-Client : The very first screen (initial config)

Controllers package - All controllers
* AbstractController - will be needed later for server communication
* ClientController - controls the initial config screen
* GoHome - Controls the main window creation
* LoginController - controls the login screen
* MainWindowController - controls the main window


GUI package
* ClientGUI
* LoginGUI
* MainWindowGUI

Models - "Abstract" classes - all inherit from AbstractModel - used for sending objects to and from the server
* AbstractModel - to send Objects to server we need Serializable 
* ClientModel - host and port config
* LoginModel - username and password config
* User - User class


=====================================
ActionListeners logic:
=====================================
The action liseners will have a funtion at the gui such as:

public void addLoginActionListener(ActionListener e)
	{
		buttonLogin.addActionListener(e);
	}
	
Which will be used in the controller, class, such as:
loginG.addLoginActionListener(new LoginListener());
The class LoginListener is defined in the controller and implements ActionListener.
This way there is no mess at the GUI class and all the logic is in the controller class.
=====================================


=============================
Example of login procedure:

Login GUI -> LoginController -> LoginListener button for the "Login button"
Once the Login button is clicked: Basic validation (Non empty fields) -> Envelope creation (message to server) -> send Envelope to server using sendToServer from AbstractController(which implements ObservableClient [OCSF] method) ->
Server gets the message and handles it via handleMessageFromClient in EchoServer ->
Send response to client via sendToClient -> Client catches response via handleMessageFromServer
============================

=============================
Important notes and shortcuts
=============================
Use refactoring instead of renaming : Alt+Shift+R


Use CTRL+SHIFT+P to find matching bracket

Use //TODO <text here> to add a flag of tood in eclipse
