package com.tgtiger.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tgtiger.API.Server;
import com.tgtiger.Alert;
import com.tgtiger.Bean.Product;
import com.tgtiger.Bill;
import com.tgtiger.FXMLTest;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
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




	private ObservableList<Bill> billList = FXCollections.observableArrayList();
	private double sum = 0;
	private int N = 0;
	
	public void setApp(FXMLTest application) {
		this.application = application;
	}

	//设置状态
	public void setState(Boolean state) {

	}

	//退出
	@FXML
	private void OUT_M(ActionEvent event) {
		application.useroutmain();
	}


	@FXML
	public void rightClick(ActionEvent event) {
		if (N >= 1) {
			billList.remove(RowRightSelection);
			billtable.refresh();
			N--;
			price.setText("总价:" + getTotalPrice(this.billList));
		}





	}

	@FXML
	private void setProduct_name(ActionEvent event) {

	}


    @FXML
    private void changeProductAmount(ActionEvent event) {

    }

    //会员检测
	@FXML
	private void Member_Check(ActionEvent event) {

		if (member_num.getText().length() == 0) {
//			new Alert().alert("请先输入会员号");
            member_information.setFill(new Color(1, 0, 0, 1));
			member_information.setText("请先输入会员号");
		} else {
			String result = new Server().checkVip(member_num.getText());
            if (result == null || result.length() == 0) {
                member_information.setFill(new Color(1,0,0,1));
                member_information.setText("获取json查询结果失败");
            } else {
                JSONObject json_rec = JSON.parseObject(result);
                if (json_rec.getInteger("status") == 0) {
                    member_information.setFill(new Color(1, 0, 0, 1));
                    member_information.setText(json_rec.getString("info"));
                } else {
                    member_information.setFill(new Color(0, 1, 0, 1));
                    member_information.setText(json_rec.getString("info"));
                }
            }

		}
	}

	//结账
	@FXML
	private void Settlement(ActionEvent event) {

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


	public ObservableList<Bill> getBillData(String name, double price_rec) {
		Bill bill = new Bill(name, 1, price_rec, price_rec);
		this.billList.add(bill);
		this.N += 1;
		return billList;
	}
	
	public void showBillTable(ObservableList<Bill> bill_list) {
		billtable.getSelectionModel().selectedIndexProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						RowRightSelection = observable.getValue().intValue();

					}
				}
		);
		product_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		/*
		 * product_name.setCellFactory(TextFieldTableCell.<Bill>forTableColumn()
		 * ); product_name.setOnEditCommit((CellEditEvent<Bill, String> t)-> {
		 * 
		 * ((Bill)t.getTableView().getItems().get(t.getTablePosition().getRow())
		 * ).setName(t.getNewValue()); });
		 */

		product_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		product_amount.setCellFactory(new Callback<TableColumn<Bill, Integer>, TableCell<Bill, Integer>>() {
			public TableCell<Bill, Integer> call(TableColumn<Bill, Integer> param) {
				return new TextFieldTableCell<>(new IntegerStringConverter());
			}
		});
		product_amount.setOnEditCommit((CellEditEvent<Bill, Integer> t) -> {
			((Bill) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAmount(t.getNewValue());
		});

		product_unitprice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
		/*
		 * product_unitprice.setCellFactory(new
		 * Callback<TableColumn<Bill,Double>, TableCell<Bill,Double>>() { public
		 * TableCell<Bill, Double> call(TableColumn<Bill, Double> param){
		 * TextFieldTableCell<Bill, Double>cell = new TextFieldTableCell<>(new
		 * DoubleStringConverter()); return cell; } });
		 * product_unitprice.setOnEditCommit((CellEditEvent<Bill, Double> t)-> {
		 * ((Bill)t.getTableView().getItems().get(t.getTablePosition().getRow())
		 * ).setUnitprice(t.getNewValue()); });
		 */

		product_subtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
		/*
		 * product_subtotal.setCellFactory(new
		 * Callback<TableColumn<Bill,Double>, TableCell<Bill,Double>>() { public
		 * TableCell<Bill, Double> call(TableColumn<Bill, Double> param){
		 * TextFieldTableCell<Bill, Double>cell = new TextFieldTableCell<>(new
		 * DoubleStringConverter()); return cell; } });
		 * product_subtotal.setOnEditCommit((CellEditEvent<Bill, Double> t)-> {
		 * ((Bill)t.getTableView().getItems().get(t.getTablePosition().getRow())
		 * ).setSubtotal(t.getNewValue()); });
		 */
	}

	public void setNumber(String no) {
		workerNo.setText(no);
	}


	public double getTotalPrice(ObservableList<Bill> bill_list){
		this.sum=0;
		for(int i=0; i<N; i++) {
			this.sum+= bill_list.get(i).getSubtotal();
		}
		return this.sum;
	}
	
	public void add_tableList(String name, Double price_rec){
		getBillData(name,price_rec);
		billtable.setItems(this.billList);
		price.setText("总价:" + getTotalPrice(this.billList));
		//System.out.println(billtable.getColumns().get(1).getCellObservableValue(0).getValue());
		//第2列第1行 get(列数).getCellObservableValue(行数)
	}

    //添加产品
    @FXML
    private void Add_Product(ActionEvent event) {
        billtable.setVisible(true);
        String result = new Server().getProduct(member_num.getText());
        if (result == null || result.length() == 0) {
            new Alert().error("和服务器通讯失败");
        } else {
            JSONObject json_rec = JSON.parseObject(result);
            Product product = json_rec.getObject("product", Product.class);
            add_tableList(product.getName(),Double.valueOf(product.getPriceSale()));
        }
    }



	@Override
	public void initialize(URL url, ResourceBundle rb) {
		showBillTable(this.billList);
	}

}