package com.tgtiger.Controller;

import com.tgtiger.FXMLTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Dialog_TrueController implements Initializable {
	private FXMLTest application;

	public void setApp(FXMLTest application) {
		this.application = application;
	}

	@FXML
	private void OUT_M(ActionEvent event) {
		application.useroutmain();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}
}
