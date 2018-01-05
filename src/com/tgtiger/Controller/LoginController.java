package com.tgtiger.Controller;

import com.tgtiger.FXMLTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
	@FXML
	private TextField account;
	@FXML
	private PasswordField password;
	private FXMLTest application;

	public void setApp(FXMLTest application) {
		this.application = application;
	}

	//
	@FXML
	public void LOGIN_M(ActionEvent event) {
		application.userlogin(account.getText(), password.getText());
	}

	//清空按钮
	@FXML
	public void CLEAR_M(ActionEvent event) {
		account.setText(null);
		password.setText(null);
	}
	
	@FXML
	public void OUT_M(ActionEvent event) {
		application.useroutmain();
	}

	@FXML
	private void REGISTER_M(ActionEvent event) {
		application.gotoregister();
	}
	
	@FXML
	private void isEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			application.userlogin(account.getText(), password.getText());
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}
}
