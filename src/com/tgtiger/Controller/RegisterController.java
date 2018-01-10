package com.tgtiger.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tgtiger.API.Server;
import com.tgtiger.utils.Alert;
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
	private TextField name;
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
		    registerCheck();
		}
	}

	//使warning显示的内容可以消失
	@FXML
	private void isType(KeyEvent event) {
		if (password1.getText().length() != 0 || password.getText().length() != 0) {
			warning.setText(null);
		}
	}


	@FXML
	private void clickRegister(ActionEvent event) {
		registerCheck();
	}

	private void registerCheck() {
		if (name.getText().length() == 0 || account.getText().length() == 0 || password.getText().length() == 0 || password1.getText().length() == 0) {
			warning.setText("输入栏不能为空");
		} else if (!password.getText().equals(password1.getText())) {
			warning.setText("两次密码不一致！");
		} else {
			warning.setText(null);
			String result = new Server().addWorker(account.getText(), password.getText(), 2, name.getText());
			if (result == null || result.length() == 0) {
//				application.gotoDialogErr("获取json为null");
				new Alert().error("获取json为null");
			} else {
				JSONObject json_rec = JSON.parseObject(result);
				int i = json_rec.getInteger("status");
				if (i == 0) {
//					application.gotodialog();
					new Alert().alert("注册成功，员工号：" + json_rec.getString("workerNo"));
				} else if (i == 1) {
//					application.gotoDialogErr("该手机号已注册，已存在工号为"+json_rec.getString("workerNo")+"的账户");
					new Alert().alert("该手机号已注册，已存在工号为" + json_rec.getString("workerNo") + "的账户");
				} else {
//					application.gotoDialogErr(json_rec.getString("info"));
					new Alert().error(json_rec.getString("info"));
				}
			}
		}
	}


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}
}
