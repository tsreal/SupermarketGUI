package com.tgtiger.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class SettleController extends CashierController implements Initializable {
	@FXML
	private TextArea settle;
	
	public void CloseSettle(ActionEvent event){
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		settle.setText(settleString);
	}
}
