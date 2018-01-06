package com.tgtiger.Controller;

import com.tgtiger.Bean.TransInfo;
import com.tgtiger.FXMLTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SystemController implements Initializable{
	
	private FXMLTest application;

	@FXML
	private Label workerNo;

	public void setApp(FXMLTest application) {
		this.application = application;
	}
	
	@FXML
	private void CASHIER(ActionEvent event){
		TransInfo info = new TransInfo();
		info.setNumber(this.workerNo.getText());
		application.gotocashier(info);
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
