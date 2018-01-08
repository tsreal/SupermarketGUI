package com.tgtiger.Controller;

import com.alibaba.fastjson.JSONObject;
import com.tgtiger.API.Server;
import com.tgtiger.Alert;
import com.tgtiger.Bean.Product;
import com.tgtiger.Bean.TransInfo;
import com.tgtiger.FXMLTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SystemController implements Initializable{
	
	private FXMLTest application;

	@FXML
	private Label workerNo;





	/*
	-----------------------------------------------------------------------------------------------------------
	商品添加 tab

	 */
		@FXML
	private Text id_name;
	@FXML
	private Text id_state;
	@FXML
	private Text id_importsale;
	@FXML
	private Text id_sale;
	@FXML
	private Text id_discount;
	@FXML
	private Text id_timeexpire;
	@FXML
	private Text id_timeproduce;
	@FXML
	private Text id_type;
	@FXML
	private Text id_provider;
	@FXML
	private Text id_producer;

	@FXML
	public void ADD_PRODUCT(ActionEvent event) {
		Product product_send = new Product();
		product_send.setDiscount(id_discount.getText());
		product_send.setType(id_type.getText());
		product_send.setName(id_name.getText());
		product_send.setState(Integer.valueOf(id_state.getText()));
		product_send.setPriceSale(id_sale.getText());
		product_send.setPriceLowest(id_importsale.getText());
		product_send.setTimeExpire(id_timeexpire.getText());
		product_send.setTimeProduce(id_timeproduce.getText());
		product_send.setProvider(id_provider.getText());
		product_send.setProducer(id_producer.getText());

		JSONObject json_send = new JSONObject();
		json_send.put("product", product_send);
		String result = new Server().addProduct(json_send);
		if (result != null || result.length() != 0) {
			JSONObject json_rec = new JSONObject();
			new Alert().alert(json_rec.getString("info"));
		} else {
			new Alert().error("通讯失败，result为null");
		}
	}

	@FXML
	public void CLEAR_ALL(ActionEvent event) {
		id_timeproduce.setText(null);
		id_discount.setText(null);
		id_timeexpire.setText(null);
		id_name.setText(null);
		id_type.setText(null);
		id_state.setText(null);
		id_sale.setText(null);
		id_importsale.setText(null);
		id_producer.setText(null);
		id_provider.setText(null);
	}






	/*
	----------------------------------------------------------------------------------------------------
	 */
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


	public void setNumber(String number) {
		workerNo.setText(number);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}
}
