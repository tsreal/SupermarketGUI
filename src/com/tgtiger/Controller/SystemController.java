package com.tgtiger.Controller;

import com.tgtiger.FXMLTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SystemController implements Initializable{
	
	private FXMLTest application;

	public void setApp(FXMLTest application) {
		this.application = application;
	}
	
	@FXML
	private void CASHIER(ActionEvent event){
		application.gotocashier();
	}
	
	public void setState(Boolean state){
		
	}
	
	@FXML
	public void OUT_M(ActionEvent event) {
		application.useroutmain();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}
}