package com.tgtiger.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tgtiger.API.Server;
import com.tgtiger.utils.Alert;
import com.tgtiger.Bean.Product;
import com.tgtiger.LocalBean.Bill;
import com.tgtiger.FXMLTest;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CashierController implements Initializable {
	private FXMLTest application;
	//条形码输入框
	@FXML
	private TextField barcode;
	//会员号输入框
	@FXML
	private TextField member_num;
	//会员信息查询反馈框
    @FXML
    private Text member_information;
    //价格显示框
	@FXML
	private Text price;
	//员工号显示标签
	@FXML
	private Label workerNo;
	//账单表
	@FXML
	private TableView<Bill> billtable;
	@FXML
	private TableColumn<Bill, String> product_name;
	@FXML
	private TableColumn<Bill, Integer> product_amount;
	@FXML
	private TableColumn<Bill, Double> product_unitprice;
	@FXML
	private TableColumn<Bill, Double> product_subtotal;



	private int RowRightSelection;

	private List<com.tgtiger.Bean.Bill.BillsEntity> billsEntityList = new ArrayList<>();
	private JSONObject json_store = new JSONObject();





	private ObservableList<Bill> billList = FXCollections.observableArrayList();
	private double sum = 0;
	private int N = 0;
	
	public void setApp(FXMLTest application) {
		this.application = application;
	}


	//退出
	@FXML
	private void OUT_M(ActionEvent event) {
		application.useroutmain();
	}

	//右键点击删除所选
	@FXML
	public void rightClick(ActionEvent event) {
		if (N >= 1) {

			String barcode_01 = json_store.getString(billtable.getItems().get(RowRightSelection).getName());

			for(int i=billsEntityList.size()-1;i>=0; i--) {
				com.tgtiger.Bean.Bill.BillsEntity billsEntity1 = billsEntityList.get(i);
				if (barcode_01.equals(billsEntity1.getBarCode())) {
					billsEntityList.remove(billsEntity1);
					System.out.println("after delete size:"+billsEntityList.size());
				}
			}

			billList.remove(RowRightSelection);
			billtable.refresh();
			N--;
			price.setText("总价:" + getTotalPrice(this.billList)+"元");
		}
	}

	//使warning显示的内容可以消失
	@FXML
	private void isType(KeyEvent event) {
			member_information.setText(null);
	}

    //会员检测
	@FXML
	private void Member_Check(ActionEvent event) {
		if (member_num.getText().length() == 0) {
			member_information.setFill(new Color(1, 0, 0, 1));
			member_information.setText("请先输入会员号");
		} else {
			String result = new Server().checkVip(member_num.getText());
			if (result == null || result.length() == 0) {
				member_information.setFill(new Color(1, 0, 0, 1));
				member_information.setText("获取json查询结果失败");
			} else {
				JSONObject json_rec = JSON.parseObject(result);
				if (json_rec.getInteger("status") == 0) {
					member_information.setFill(new Color(0, 1, 0, 1));
					member_information.setText(json_rec.getString("info"));
				} else {
					member_information.setFill(new Color(1, 0, 0, 1));
					member_information.setText(json_rec.getString("info"));
				}
			}
		}
	}

	//结账
	@FXML
	private void Settlement(ActionEvent event) {

		String result = new Server().checkVip(member_num.getText());
		JSONObject json_rec = JSON.parseObject(result);
		boolean b = false;
		if (json_rec.getBoolean("task")) {
			b = true;
		}

		if (billsEntityList.size() == 0) {
			new Alert().alert("请先添加商品");
		} else {
			String result_02 = new Server().getBill(billsEntityList, b, member_num.getText());
			System.out.println(result_02);
			json_rec = JSON.parseObject(result_02);
			if (json_rec.getInteger("status") == 0) {
				new Alert().alert(json_rec.getString("info"));
			} else {
				new Alert().error(json_rec.getString("info"));
			}
			settleString = "收银员：" + workerNo.getText() + "\t\t   " + "日期：" + display_time.getText() + "\n";
			settleString += "\n========================\n\n";
			settleString += billtable.getColumns().get(0).getText() + "\t\t"
					+billtable.getColumns().get(1).getText() + "\t\t"
					+billtable.getColumns().get(2).getText() + "\t\t"
					+ billtable.getColumns().get(3).getText() + "\n";
			for (int i = 0; i < N; i++) {

				settleString += "\n" + billtable.getItems().get(i).getName()
						+ "\n\t\t\t  " + billtable.getItems().get(i).getAmount()
						+ "\t\t" + billtable.getItems().get(i).getUnitprice()
						+ "\t\t" + billtable.getItems().get(i).getSubtotal()
						+ "\n------------------------------------------------------\n";
			}
			settleString += "\n========================\n\n";
			settleString += "\n\t\t\t\t\t       " + price.getText();
			System.out.println(settleString);
			setSettle();

			//订单完成重置
			billList.clear();
			billsEntityList = new ArrayList<>();
			json_store = new JSONObject();
			price.setText("总价: 0元");
			barcode.setText(null);
			sum = 0;
			N = 0;
		}





	}

	//清空输入栏
	@FXML
	public void Clear_one(ActionEvent event) {
		barcode.setText(null);
	}
	@FXML
	public void Clear_two(ActionEvent event) {
		member_num.setText(null);
	}


	private void getBillData(String name, double price_rec) {
		Bill bill = new Bill(name, 1, price_rec, price_rec);
		this.billList.add(bill);
		this.N += 1;
	}




	private void showBillTable(ObservableList<Bill> bill_list) {
		billtable.getSelectionModel().selectedIndexProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						RowRightSelection = observable.getValue().intValue();
					}
				}
		);

		product_name.setCellValueFactory(new PropertyValueFactory<>("name"));

		product_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

		product_amount.setCellFactory(new Callback<TableColumn<Bill, Integer>, TableCell<Bill, Integer>>() {
			public TableCell<Bill, Integer> call(TableColumn<Bill, Integer> param) {
				return new TextFieldTableCell<>(new IntegerStringConverter());
			}
		});

		product_amount.setOnEditCommit((CellEditEvent<Bill, Integer> t) -> {
			((Bill) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAmount(t.getNewValue());
			((Bill) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSubtotal(t.getRowValue().getAmount() * t.getRowValue().getUnitprice());
			billtable.refresh();
			price.setText("总价:" + getTotalPrice(this.billList) + "元");
		});

		product_unitprice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));

		product_subtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

	}




	private double getTotalPrice(ObservableList<Bill> bill_list){
		this.sum=0;
		for(int i=0; i<N; i++) {
			this.sum+= bill_list.get(i).getSubtotal();
		}
		return this.sum;
	}
	
	private void add_tableList(String name, Double price_rec){

		int flag = 0;
		for(int i=0; i<N; i++) {

			//将相同商品置于同一行
			if (billtable.getItems().get(i).getName().equals(name)) {
				flag = 1;
				billtable.getItems().get(i).setAmount(billtable.getItems().get(i).getAmount() + 1);
				billtable.getItems().get(i).setSubtotal(billtable.getItems().get(i).getUnitprice()*billtable.getItems().get(i).getAmount());
				billtable.refresh();
				price.setText("总价:" + getTotalPrice(this.billList)+"元");
			}
		}
		if (flag != 1) {
			getBillData(name, price_rec);
			billtable.setItems(this.billList);
			price.setText("总价:" + getTotalPrice(this.billList)+"元");
		}

		//System.out.println(billtable.getColumns().get(1).getCellObservableValue(0).getValue());
		//第2列第1行 get(列数).getCellObservableValue(行数)

	}

    //添加产品
    @FXML
    private void Add_Product(ActionEvent event) {
		if (barcode.getText().length() == 0) {
			new Alert().error("请先扫描或者输入条形码");
		} else {
			String code = barcode.getText();
			String result = new Server().getProduct(code);
			if (result == null || result.length() == 0) {
				new Alert().error("和服务器通讯失败");
			} else {
				System.out.println(result);




				JSONObject json_rec = JSON.parseObject(result);
				if (json_rec.getBoolean("task")) {

					//将商品条码和条码数量
					int flag_a = 0;
					for (int i = 0; i < billsEntityList.size(); i++) {
						if (billsEntityList.get(i).getBarCode().equals(code)) {
							billsEntityList.get(i).setNumber(billsEntityList.get(i).getNumber() + 1);
							flag_a = 1;
						}
					}
					if (flag_a == 0) {

						com.tgtiger.Bean.Bill.BillsEntity radom = new com.tgtiger.Bean.Bill.BillsEntity();
						radom.setNumber(1);
						radom.setBarCode(code);
						billsEntityList.add(radom);
					}


					Product product = json_rec.getObject("product", Product.class);
					json_store.put(product.getName(), code);
					add_tableList(product.getName(), Double.valueOf(product.getPriceSale()));
				} else {
					new Alert().error(json_rec.getString("info"));
				}
			}
		}

    }

    @FXML
	private Label display_time;


	public void setSystemTime() {

		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		display_time.setText(df.format(now));
	}

	public static String settleString;


	public void setSettle() {
		Parent target = null;
		try {
			target = FXMLLoader.load(getClass().getResource("../fxml/sattle.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(target);
		Stage stg = new Stage();
		stg.setTitle("超市管理系统");
		stg.setScene(scene);
		stg.setResizable(false);
		stg.show();
	}




	//设置员工号码
	public void setNumber(String no) {
		workerNo.setText(no);
	}

	public void initPrice() {
		price.setText("总价： 0元");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		showBillTable(this.billList);
		setSystemTime();
	}

}