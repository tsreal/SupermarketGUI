package com.tgtiger.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SettleController extends CashierController implements Initializable {
	@FXML
	private TextArea settle;

	//	public void CloseSettle(ActionEvent event){
//		((Node)(event.getSource())).getScene().getWindow().hide();
//	}
	@FXML
	private Button button;

	@FXML
	public void Sattle_out(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			button.getScene().getWindow().hide();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		settle.setText(settleString);
		System.out.println("2"+settleString);

	}
}
