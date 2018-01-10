package com.tgtiger;

import com.tgtiger.API.Server;
import com.tgtiger.Bean.TransInfo;
import com.tgtiger.Bean.Worker;
import com.tgtiger.Controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLTest extends Application {
	private Stage stage;
	private int level = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("超市管理系统");
		double MINIMUM_WINDOW_WIDTH = 400.0;
		stage.setWidth(MINIMUM_WINDOW_WIDTH);
		double MINIMUM_WINDOW_HEIGHT = 250.0;
		stage.setHeight(MINIMUM_WINDOW_HEIGHT);
		gotologin();
		stage.setResizable(false);
		stage.show();
	}

	public void gotologin() {//登录界面
		try {
			LoginController login = (LoginController) replaceSceneContent("./fxml/Login.fxml");
			login.setApp(this);
		} catch (Exception ex) {
			Logger.getLogger(FXMLTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void gotoregister(){//注册界面
		try{
			RegisterController register = (RegisterController) replaceSceneContent("./fxml/register.fxml");
			register.setApp(this);
		}catch(Exception ex){
			Logger.getLogger(FXMLTest.class.getName()).log(Level.SEVERE, null,ex);
		}
	}

	public void gotocashier(TransInfo info) {//收银界面
		try {
			CashierController cashier = (CashierController) replaceSceneContent("./fxml/cashier.fxml");
			cashier.setApp(this);
			cashier.setNumber(info.getNumber());
			cashier.initPrice();
			/*if (0 == level) {
				cashier.setState(true);
			}
			else {
				cashier.setState(false);
			}*/
		} catch (Exception ex) {
			Logger.getLogger(FXMLTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void gotosystem(TransInfo info) {//系统界面
		try {
			SystemController system = (SystemController) replaceSceneContent("./fxml/system.fxml");
			system.setApp(this);
			system.setNumber(info.getNumber());
			/*if (0 == level) {
				system.setState(true);
			}
			else {
				system.setState(false);
			}*/
		} catch (Exception ex) {
			Logger.getLogger(FXMLTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void userlogin(String account, String password) {
		//登录判断账号类型
		if (account.length()==0|| password.length()==0 ) {
			new Alert().error("输入栏不能为空");
		} else {
			Worker worker = new Server().workerLogin(account, password);
			if (worker.getYes() == 0) {
				TransInfo info = new TransInfo();
				info.setNumber(worker.getWorkerNo());

				if (worker.getLevel() == 0 || worker.getLevel() == 1) {
					gotosystem(info);
				} else {
					gotocashier(info);
				}
			} else if (worker.getYes() == 3) {
				new Alert().error("错误3:获取json为null");
			} else if (worker.getYes() == 2) {
				new Alert().error("错误2：账号不存在,请先注册");
			} else if (worker.getYes() == 1) {
				new Alert().error("错误1：密码错误，请重试");
			} else {
			    new Alert().error("未知错误");
			}
		}
	}




	public void useroutmain() {//返回登录界面
		gotologin();
	}
	
	private void setSize(Stage stage) {//设置窗口位置
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		double minX = bounds.getMinX();
		double minY = bounds.getMinY();
		double maxX = bounds.getMaxX();
		double maxY = bounds.getMaxY();	
		stage.setX((minX + maxX - stage.getWidth()) / 2);
		stage.setY((minY + maxY - stage.getHeight()) / 2);

	}

	public Initializable replaceSceneContent(String fxml) throws Exception {//读入fxml文件
		FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(FXMLTest.class.getResource(fxml));
		AnchorPane page;
        try (InputStream in = FXMLTest.class.getResourceAsStream(fxml)) {
            page = loader.load(in);
        }
		Scene scene = new Scene(page);
		stage.setScene(scene);
		stage.sizeToScene();
		setSize(stage);
		return (Initializable) loader.getController();
	}




	public static void main(String[] args) {
		launch(args);
	}
}
