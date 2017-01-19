package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.ReportsGUI;
import gui.UserReportGUI;

public class ReportsController extends AbstractController {
	
	private ReportsController reportsController;
	private ReportsGUI reportsGUI;
	
	 public ReportsController(ReportsGUI rg)
	 {
		 reportsGUI = rg;
		 reportsController = this;
		 rg.AddButtonUserReportActionListener(new UserReportListener());
	 }
	 
	 
	 class UserReportListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e){
				reportsGUI.dispose();
				new UserReportController(new UserReportGUI());
			}
		}
	 
	 

}
