package com.tgtiger.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tgtiger.API.Server;
import com.tgtiger.Alert;
import com.tgtiger.Bean.Member;
import com.tgtiger.Bean.Product;
import com.tgtiger.Bean.TransInfo;
import com.tgtiger.FXMLTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SystemController implements Initializable {


	public static void main(String[] args) {
		Product product_send = new Product();
		product_send.setDiscount("0.95");
		product_send.setType("食品");
		product_send.setName("可口可乐-330ml");
		product_send.setState(0);
		product_send.setPriceSale("2.5");
		product_send.setPriceLowest("2.0");
		product_send.setTimeExpire("2018-07-31");
		product_send.setTimeProduce("2018-07-31");
		product_send.setProvider("太原食品批发市场");
		product_send.setProducer("可口可乐（山西）饮料有限公司");

		JSONObject json = new JSONObject();
		json.put("product", product_send);
		System.out.println(json.toString());


	}


	private FXMLTest application;

	@FXML
	private Label workerNo;



	/*
	-------------------------------------------
	 */

	@FXML
	private TableColumn<Member,String> member_number;
	@FXML
	private TableColumn<Member,String> member_name;
	@FXML
	private TableColumn<Member,String> member_phone;
	@FXML
	private TableColumn<Member,String> member_expire;
	@FXML
	private TableColumn<Member,String> member_date_signup;
	@FXML
	private TableColumn<Member,String> member_cost;

	@FXML
	private TableView<Member> memberTable;


	private ObservableList<Member> memberList = FXCollections.observableArrayList();

	public ObservableList<Member> setMemberList() {
		Member member = new Member();
		member.setExpire(false);
		member.setName("王腾腾");
		member.setMemberNo("20150240113");
		member.setDateSignUp("2015-02-01");
		member.setPhone("18435189097");
		this.memberList.add(member);
		return memberList;
	}

	public void showMemberTable(ObservableList<Member> memberList) {
		member_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		member_number.setCellValueFactory(new PropertyValueFactory<>("number"));
		member_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		member_expire.setCellValueFactory(new PropertyValueFactory<>("expire"));
		member_date_signup.setCellValueFactory(new PropertyValueFactory<>("dateSignup"));
		member_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

	}













	/*
	-----------------------------------------------------------------------------------------------------------
	商品添加 tab

	 */
	@FXML
	private TextField id_name;
	@FXML
	private TextField id_state;
	@FXML
	private TextField id_importsale;
	@FXML
	private TextField id_sale;
	@FXML
	private TextField id_discount;
	@FXML
	private TextField id_timeexpire;
	@FXML
	private TextField id_timeproduce;
	@FXML
	private TextField id_type;
	@FXML
	private TextField id_provider;
	@FXML
	private TextField id_producer;

	@FXML
	public void ADD_PRODUCT(ActionEvent event) {
		if (id_discount.getText().length() == 0 || id_type.getText().length() == 0
				|| id_name.getText().length() == 0 || id_state.getText().length() == 0
				|| id_sale.getText().length() == 0 || id_importsale.getText().length() == 0
				|| id_timeexpire.getText().length() == 0 || id_timeproduce.getText().length() == 0
				|| id_provider.getText().length() == 0 || id_producer.getText().length() == 0) {
			new Alert().error("输入栏不能为空");
		} else {
			Product product_send = new Product();
			product_send.setDiscount(id_discount.getText());
			product_send.setType(id_type.getText());
			product_send.setName(id_name.getText());
			product_send.setState(Integer.parseInt(id_state.getText()));
			product_send.setPriceSale(id_sale.getText());
			product_send.setPriceLowest(id_importsale.getText());
			product_send.setTimeExpire(id_timeexpire.getText());
			product_send.setTimeProduce(id_timeproduce.getText());
			product_send.setProvider(id_provider.getText());
			product_send.setProducer(id_producer.getText());


			JSONObject json_send = new JSONObject();
			json_send.put("product", product_send);
			System.out.println(json_send.toString());
			String result = new Server().addProduct(json_send.toString());
			System.out.println(result);
			if (result == null || result.length() == 0) {
				new Alert().error("通讯失败，result为null");
			} else {
				JSONObject json_rec = new JSONObject();
				json_rec = JSON.parseObject(result);
				new Alert().alert(json_rec.getString("info"));
			}
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
	private void CASHIER(ActionEvent event) {
		TransInfo info = new TransInfo();
		info.setNumber(this.workerNo.getText());
		application.gotocashier(info);
	}

	public void setState(Boolean state) {

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
		showMemberTable(this.memberList);
	}
}
