package com.tgtiger.Controller;

import com.tgtiger.Bill;
import com.tgtiger.FXMLTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class CashierController implements Initializable {
	private FXMLTest application;
	@FXML
	private TextField barcode;
	@FXML
	private TextField member_num;
	@FXML
	private Text price;
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
	@FXML
	private Text member_information;
	private ObservableList<Bill> billList = FXCollections.observableArrayList();
	private double sum = 0;
	private int N = 0;
	
	public void setApp(FXMLTest application) {
		this.application = application;
	}

	public void setState(Boolean state) {

	}

	@FXML
	private void OUT_M(ActionEvent event) {
		application.useroutmain();
	}

	@FXML
	private void Add_Product(ActionEvent event) {
		billtable.setVisible(true);
		add_tableList();
	}

	@FXML
	private void setProduct_name(ActionEvent event) {

	}

	@FXML
	private void Member_Check(ActionEvent event) {

	}

	@FXML
	private void Settlement(ActionEvent event) {

	}
	
	public ObservableList<Bill> getBillData() {
		Bill bill = new Bill("1", 2, 1.5, 3);
		this.billList.add(bill);
		this.N += 1;
		return billList;
	}
	
	public void showBillTable(ObservableList<Bill> bill_list) {
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
	public double getTotalPrice(ObservableList<Bill> bill_list){
		return  this.sum += bill_list.get(N - 1).getSubtotal();
	}
	
	public void add_tableList(){
		getBillData();
		billtable.setItems(this.billList);
		price.setText("总价:" + getTotalPrice(this.billList));
		//System.out.println(billtable.getColumns().get(1).getCellObservableValue(0).getValue());
		//第2列第1行 get(列数).getCellObservableValue(行数)
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		showBillTable(this.billList);
	}

}