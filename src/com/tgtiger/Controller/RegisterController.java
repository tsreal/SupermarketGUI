package com.tgtiger.Controller;

import com.tgtiger.FXMLTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
	@FXML
	private TextField account;
	@FXML
	private PasswordField password = null;
	@FXML
	private PasswordField password1 = null;
	@FXML
	private Text warning;
	private FXMLTest application;

	public void setApp(FXMLTest application) {
		this.application = application;
	}

	@FXML
	private void CLEAR_M(ActionEvent event) {
		account.setText(null);
		password.setText(null);
		password1.setText(null);
	}

	@FXML
	private void OUT_M(ActionEvent event) {
		application.useroutmain();
	}

	@FXML
	private void isEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			application.userlogin(account.getText(), password.getText());
		}
	}

	@FXML
	private void setDialog(ActionEvent event) {
		if (password.getText().length() > 0 && password1.getText().length() > 0) {
			if (!password.getText().equals(password1.getText())) {
				warning.setText("两次密码不一致！");
				return;
			} else {
				warning.setText(null);
				application.gotodialog();
			}
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}
}
