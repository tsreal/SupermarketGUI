package com.tgtiger.Controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tgtiger.*;
import com.tgtiger.API.Server;
import com.tgtiger.Bean.*;
import com.tgtiger.LocalBean.Depository;
import com.tgtiger.LocalBean.Member;
import com.tgtiger.LocalBean.Sales_Report;
import com.tgtiger.LocalBean.Worker;
import com.tgtiger.utils.Alert;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class SystemController implements Initializable {

	private FXMLTest application;
	@FXML
	private Label system_date;
	@FXML
	private Label choice_date;
	@FXML
	private Label workerNo;
	private int selection;
	/*
	 * 会员账号管理
	 */
	@FXML
	private TableView<Member> member_table;
	@FXML
	private TableColumn<Member, String> member_name;
	@FXML
	private TableColumn<Member, String> member_No;
	@FXML
	private TableColumn<Member, String> member_phone;
	@FXML
	private TableColumn<Member, Double> member_consumption;
	@FXML
	private TableColumn<Member, String> member_dateSignUp;
	@FXML
	private TableColumn<Member, Boolean> member_isexpire;
	private ObservableList<Member> memberlist = FXCollections.observableArrayList();
	/*
	 * 
	 */

	/*
	 * 商品添加
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

	/*
	 * 销售报表
	 */
	@FXML
	private TableView<Sales_Report> sales_table;
	@FXML
	private TableColumn<Sales_Report, String> sales_name;
	@FXML
	private TableColumn<Sales_Report, Double> sales_unitprice;
	@FXML
	private TableColumn<Sales_Report, Integer> sales_amount;
	@FXML
	private TableColumn<Sales_Report, Integer> ordinary_sales;
	@FXML
	private TableColumn<Sales_Report, Integer> member_sales;
	@FXML
	private TableColumn<Sales_Report, Double> sales_subtotal;
	@FXML
	private ChoiceBox<String> choiceId;
	@FXML
	private TextField year;
	@FXML
	private TextField month;
	@FXML
	private TextField day;
	private static int choice; // 年、月、日选择
	private ObservableList<Sales_Report> saleslist = FXCollections.observableArrayList();
	/*
	 * 
	 */

	/*
	 * 库存管理
	 */
	@FXML
	private TableView<Depository> dep_table;
	@FXML
	private TableColumn<Depository, String> dep_name;
	@FXML
	private TableColumn<Depository, Integer> dep_amount;
	@FXML
	private TableColumn<Depository, Integer> dep_importNo;
	@FXML
	private TableColumn<Depository, String> dep_importDate;
	@FXML
	private TableColumn<Depository, Boolean> dep_isimport;
	private ObservableList<Depository> deplist = FXCollections.observableArrayList();
	/*
	 * 
	 */

	/*
	 * 员工账号管理
	 */
	@FXML
	private TableView<Worker> worker_table;
	@FXML
	private TableColumn<Worker, String> worker_name;
	@FXML
	private TableColumn<Worker, String> worker_No;
	@FXML
	private TableColumn<Worker, String> worker_phone;
	@FXML
	private TableColumn<Worker, String> worker_dateSignUp;
	@FXML
	private TableColumn<Worker, Integer> worker_level;
	private ObservableList<Worker> workerlist = FXCollections.observableArrayList();

	/*
	 * 
	 */
	public void setApp(FXMLTest application) {
		this.application = application;
	}


	//转到收银界面
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

	/*
	 * 会员账号管理
	 */
	public void showMemberTable() {// 初始化报表表格
		member_table.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				selection = observable.getValue().intValue();
			}
		});
		member_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		member_name.setCellFactory(TextFieldTableCell.<Member> forTableColumn());
		member_name.setOnEditCommit((CellEditEvent<Member, String> t) -> {
			((Member) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
		});

		member_No.setCellValueFactory(new PropertyValueFactory<>("memberNo"));
		member_No.setCellFactory(TextFieldTableCell.<Member> forTableColumn());
		member_No.setOnEditCommit((CellEditEvent<Member, String> t) -> {
			((Member) t.getTableView().getItems().get(t.getTablePosition().getRow())).setMemberNo(t.getNewValue());
		});

		member_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		member_phone.setCellFactory(TextFieldTableCell.<Member> forTableColumn());
		member_phone.setOnEditCommit((CellEditEvent<Member, String> t) -> {
			((Member) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPhone(t.getNewValue());
		});

		member_consumption.setCellValueFactory(new PropertyValueFactory<>("consumption"));
		member_consumption.setCellFactory(new Callback<TableColumn<Member, Double>, TableCell<Member, Double>>() {
			public TableCell<Member, Double> call(TableColumn<Member, Double> param) {
				TextFieldTableCell<Member, Double> cell = new TextFieldTableCell<>(new DoubleStringConverter());
				return cell;
			}
		});
		member_consumption.setOnEditCommit((CellEditEvent<Member, Double> t) -> {
			((Member) t.getTableView().getItems().get(t.getTablePosition().getRow())).setConsumption(t.getNewValue());
		});

		member_dateSignUp.setCellValueFactory(new PropertyValueFactory<>("dateSignUp"));
		/*
		 * member_dateSignUp.setCellFactory(TextFieldTableCell.<Member>
		 * forTableColumn());
		 * member_dateSignUp.setOnEditCommit((CellEditEvent<Member, String> t)
		 * -> { ((Member)
		 * t.getTableView().getItems().get(t.getTablePosition().getRow())).
		 * setDateSignUp(t.getNewValue()); });
		 */
		member_isexpire.setCellValueFactory(new PropertyValueFactory<>("isexpire"));
	}

	private ObservableList<Member> setMemberData() {
		MemberList list_member;
		String receive = new Server().getAllMember();

		//含有memlists,task
		list_member = JSON.parseObject(receive, MemberList.class);
		// for循环存入所有员工信息
		for (int i = 0; i <list_member.getMemlists().size() ; i++) {
			Member member = new Member(list_member.getMemlists().get(i).getName(), list_member.getMemlists().get(i).getMemberNo(),
					list_member.getMemlists().get(i).getPhone(), Double.valueOf(list_member.getMemlists().get(i).getBill()),
					list_member.getMemlists().get(i).getDateSignUp(), list_member.getMemlists().get(i).isExpire());
			this.memberlist.add(member);
		}
		return memberlist;
	}

	@FXML
	private TextField Member_TextField;
	@FXML
	public void Member_Search(ActionEvent event) {
		if (Member_TextField.getText() == null || Member_TextField.getText().equals("")) {
			new Alert().alert("请先输入！");
		} else {
			String result = new Server().getMember(Member_TextField.getText());
			if (result == null) {
				new Alert().alert("检查服务器！");
			} else {
				JSONObject json_rec = new JSONObject();
				System.out.println("result:"+result);

				json_rec = JSON.parseObject(result);
				System.out.println(json_rec);
				if (json_rec.getInteger("status") == 0) {
					com.tgtiger.Bean.Member beanMember = json_rec.getObject("member", com.tgtiger.Bean.Member.class);
					this.memberlist.clear();
					this.memberlist.add(new Member(beanMember.getName(), beanMember.getMemberNo(), beanMember.getPhone(),
							Double.valueOf(beanMember.getBill()), beanMember.getDateSignUp(), beanMember.isExpire()));
					member_table.setItems(memberlist);
					new Alert().alert("检索成功");
				} else {
					new Alert().error(json_rec.getString("info"));
				}

			}
		}
	}

	@FXML
	public void Member_Addall(ActionEvent event) {//显示所有会员信息
		this.memberlist.clear();
		member_table.setItems(setMemberData());
	}


	@FXML
	public void Clear_Member_TextField(ActionEvent event) {
		Member_TextField.setText(null);
	}

	//增加会员
	public void Member_Add(ActionEvent event) throws IOException {
		memberlist.clear();
		if (memberlist.isEmpty()) {
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date = df.format(now);
			Member member = new Member("输入姓名", "***", "输入电话", 0, date, false);
			this.memberlist.add(member);
			member_table.setItems(memberlist);
		}

//		Date now = new Date();
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		String date = df.format(now);
//		Member member = new Member("输入姓名", "***", "输入电话", 0, date, false);
//		this.memberlist.add(member);
//		member_table.setItems(memberlist);



//		ObservableList<Stage> stage = FXRobotHelper.getStages();
//		Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../fxml/memberadd.fxml")));
//		stage.get(0).setScene(scene);
	}
	
	public void Member_Delete(ActionEvent event) {//删除会员
		memberlist.remove(selection);
		member_table.refresh();
	}

	public void Member_Submit(ActionEvent event) {
		String name = memberlist.get(memberlist.size()-1).getName();
		String phone = memberlist.get(memberlist.size() - 1).getPhone();
		System.out.println(name+":"+phone);
		String result = new Server().addMember(phone, name);
		JSONObject json_rec;
		json_rec = JSON.parseObject(result);
		if (json_rec.getBoolean("task")) {
			new Alert().alert(json_rec.getString("info")+"会员号："+json_rec.getString("memberNo"));
		} else {
			new Alert().error(json_rec.getString("info"));

		}

	}

	
	/*
	 * 
	 */

	@FXML
	public void ADD_PRODUCT() {
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
	public void Clear_Product() {
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
	 * 销售报表
	 */
	@FXML
	public ObservableList<Sales_Report> setSalesData(String name,int totalNo,int normalNo,int vipNo,double price,double income) {
		// for循环存入报表

		Sales_Report sales = new Sales_Report(name, totalNo, normalNo, vipNo, price, income);
//		sales.setSubtotal(sales.getAmount() * sales.getUnitprice());
		this.saleslist.add(sales);

		return saleslist;
	}

	@FXML
	public void QUERY_REPORT(ActionEvent event) {// 报表查询
		if (month.getText().length() == 1) {
			month.setText("0" + month.getText());
		}
		if (day.getText().length() == 1) {
			day.setText("0" + day.getText());
		}
		if (year.getText().length() == 4 && month.getText().length() == 2 && day.getText().length() == 2) {
			String date = year.getText() + "-" + month.getText() + "-" + day.getText();
			SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
			Date now = new Date();
			String nowString = df.format(now);
			Date d_now = null;
			Date d_input = null;
			try {
				d_now = df.parse(nowString);
				d_input = df.parse(date);
				if (d_input.getTime() > d_now.getTime()) {
					new Alert().error("无法查询未来天数");
				} else {
					if (choice == 0 || choice == 1 || choice == 2) {
						String result = new Server().getAnalysis(choice, date);
						JSONObject json_rec = JSON.parseObject(result);
						if (json_rec.getInteger("status") == 0) {
							saleslist.clear();
							StatementList statementList = JSON.parseObject(result, StatementList.class);
							for (int i = 0; i < statementList.getLists().size(); i++) {
								setSalesData(statementList.getLists().get(i).getName(), statementList.getLists().get(i).getTotalNo(),
										statementList.getLists().get(i).getNormalNo(), statementList.getLists().get(i).getVipNo(),
										statementList.getLists().get(i).getPriceSale(), statementList.getLists().get(i).getIncome());
							}
							sales_table.setItems(saleslist);
							if (choice == 0) {
								df = new SimpleDateFormat("yyyy");
								choice_date.setText(df.format(d_input)+"年");
							}
							if (choice == 1) {
								df = new SimpleDateFormat("yyyy-MM");
								choice_date.setText(df.format(d_input)+"月");
							}
							if (choice == 2) {
								df = new SimpleDateFormat("yyyy-MM-dd");
								choice_date.setText(df.format(d_input)+"日");
							}
							new Alert().alert("查找成功！");
						} else {
							new Alert().error(json_rec.getString("info"));
						}

					}



				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			/*
			 * send to server
			 */
		} else {
			new Alert().error("日期输入错误！");
		}
	}

	public void setSystemTime() {// 初始化系统时间
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		system_date.setText(df.format(now));
		choice_date.setText(df.format(now));
	}

	public void setChoiceId() {// 初始化选择框
		choiceId.setItems(FXCollections.observableArrayList("年表", "月表", "日表"));
		choiceId.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				choice = newValue.intValue();
				switch (choice) {
					//year
				case 0:
					month.setDisable(true);
					month.setText("01");
					day.setDisable(true);
					day.setText("01");
					break;
				case 1:
					//month
					month.setDisable(false);
					day.setDisable(true);
					day.setText("01");
					break;
				case 2:
					//day
					month.setDisable(false);
					day.setDisable(false);
					break;
				default:
					break;
				}
			}
		});
	}

	public void showReportTable() {// 初始化报表表格
		sales_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		sales_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		ordinary_sales.setCellValueFactory(new PropertyValueFactory<>("ordinary_sales"));
		member_sales.setCellValueFactory(new PropertyValueFactory<>("member_sales"));
		sales_unitprice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
		sales_subtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
	}

	/*
	 * 
	 */

	/*
	 * 库存管理
	 */
	public void showDepTable() {// 初始化库存表格
		dep_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		dep_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		dep_importNo.setCellValueFactory(new PropertyValueFactory<>("importNo"));
		dep_importDate.setCellValueFactory(new PropertyValueFactory<>("importDate"));
		dep_isimport.setCellFactory((col) -> {
			TableCell<Depository, Boolean> cell = new TableCell<Depository, Boolean>() {

				@Override
				public void updateItem(Boolean item, boolean empty) {
					super.updateItem(item, empty);
					this.setText(null);
					this.setGraphic(null);

					if (!empty) {
						CheckBox checkBox = new CheckBox();
						this.setGraphic(checkBox);
						checkBox.selectedProperty().addListener((obVal, oldVal, newVal) -> {
							if (newVal) {
								System.out.println("第" + this.getIndex() + "行被选中！");
								System.out.println(newVal.booleanValue());
							}
						});
					}
				}

			};
			return cell;
		});
	}

	public ObservableList<Depository> setDepData(String name,int number,int defaultNo, String date) {
		// for循环存入所有库存信息

			Depository dep = new Depository(name,number,defaultNo,date);
			this.deplist.add(dep);

		return deplist;
	}

	@FXML
	public void Dep_ALL(ActionEvent event) {
		deplist.clear();

		String result = new Server().getRemain();
		if (result != null && result.length() > 0) {
			JSONObject json_rec = JSON.parseObject(result);
			if (json_rec.getBoolean("status")) {
				DepositoryList depositoryList = new DepositoryList();
				depositoryList = JSON.parseObject(result, DepositoryList.class);
				for(int i=0; i<depositoryList.getLists().size(); i++) {
					setDepData(depositoryList.getLists().get(i).getName(), depositoryList.getLists().get(i).getProductNo(),
							depositoryList.getLists().get(i).getImportNo(), depositoryList.getLists().get(i).getImportTime());
				}
				dep_table.setItems(deplist);
			}
		} else {
			new Alert().error("查询失败！");
		}

	}

	/*
	 * 
	 */

	/*
	 * 员工账号管理
	 */
	public void showWorkerTable() {// 初始化员工表格
		worker_table.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				selection = observable.getValue().intValue();
			}
		});
		worker_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		worker_name.setCellFactory(TextFieldTableCell.<Worker> forTableColumn());
		worker_name.setOnEditCommit((CellEditEvent<Worker, String> t) -> {
			((Worker) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
		});

		worker_No.setCellValueFactory(new PropertyValueFactory<>("workerNo"));
		worker_No.setCellFactory(TextFieldTableCell.<Worker> forTableColumn());
		worker_No.setOnEditCommit((CellEditEvent<Worker, String> t) -> {
			((Worker) t.getTableView().getItems().get(t.getTablePosition().getRow())).setWorkerNo(t.getNewValue());
		});

		worker_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		worker_phone.setCellFactory(TextFieldTableCell.<Worker> forTableColumn());
		worker_phone.setOnEditCommit((CellEditEvent<Worker, String> t) -> {
			((Worker) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPhone(t.getNewValue());
		});

		worker_dateSignUp.setCellValueFactory(new PropertyValueFactory<>("dateSignUp"));
		/*
		 * worker_dateSignUp.setCellFactory(TextFieldTableCell.<Worker>
		 * forTableColumn());
		 * worker_dateSignUp.setOnEditCommit((CellEditEvent<Worker, String> t)
		 * -> { ((Worker)
		 * t.getTableView().getItems().get(t.getTablePosition().getRow())).
		 * setDateSignUp(t.getNewValue()); });
		 */



		worker_level.setCellValueFactory(new PropertyValueFactory<>("level"));
		worker_level.setCellFactory(new Callback<TableColumn<Worker, Integer>, TableCell<Worker, Integer>>() {
			public TableCell<Worker, Integer> call(TableColumn<Worker, Integer> param) {
				TextFieldTableCell<Worker, Integer> cell = new TextFieldTableCell<>(new IntegerStringConverter());
				return cell;
			}
		});
		worker_level.setOnEditCommit((CellEditEvent<Worker, Integer> t) -> {
			((Worker) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLevel(t.getNewValue());
		});

	}

	public ObservableList<Worker> setWorkerData() {
//		// for循环存入所有员工信息
//		for (int i = 0; i < 3; i++) {
//			Worker worker = new Worker("王腾腾" + i, "0000" + i, "1842518293" + i, "2018-01-0" + i, i);
//			this.workerlist.add(worker);
//		}
//		return workerlist;


		WorkerList list_worker;
		String receive = new Server().getAllWorker();

		//含有memlists,task
		list_worker = JSON.parseObject(receive, WorkerList.class);
		// for循环存入所有员工信息
		for (int i = 0; i <list_worker.getWorkerlists().size() ; i++) {
			Worker worker = new Worker(list_worker.getWorkerlists().get(i).getName(), list_worker.getWorkerlists().get(i).getWorkerNo(),
					list_worker.getWorkerlists().get(i).getPhone(), list_worker.getWorkerlists().get(i).getDateSignUp(),list_worker.getWorkerlists().get(i).getLevel());
			this.workerlist.add(worker);
		}
		return workerlist;
	}

	@FXML
	private TextField Worker_TextField;
	@FXML
	public void Worker_Search(ActionEvent event) {
		if (Worker_TextField.getText() == null || Worker_TextField.getText().equals("")) {
			new Alert().alert("请先输入！");
		} else {
			String result = new Server().getWorker(Worker_TextField.getText());
			if (result == null) {
				new Alert().alert("检查服务器！");
			} else {
				JSONObject json_rec = new JSONObject();
				System.out.println("result:"+result);

				json_rec = JSON.parseObject(result);
				System.out.println(json_rec);
				if (json_rec.getInteger("status") == 0) {
					com.tgtiger.Bean.Worker beanWorker = json_rec.getObject("worker", com.tgtiger.Bean.Worker.class);
					this.workerlist.clear();
					this.workerlist.add(new Worker(beanWorker.getName(), beanWorker.getWorkerNo(), beanWorker.getPhone(),
							beanWorker.getDateSignUp(), beanWorker.getLevel()));
					worker_table.setItems(workerlist);
					new Alert().alert("检索成功");
				} else {
					new Alert().error(json_rec.getString("info"));
				}

			}
		}
	}

	@FXML
	public void Worker_Addall(ActionEvent event) {//显示所有员工信息
		this.workerlist.clear();
		worker_table.setItems(setWorkerData());
	}

	public void Worker_Add(ActionEvent event) {//增加员工
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(now);
//		Worker worker = new Worker("姓名", "5位数字", "11位数字", date, "密码", 0);
//		this.workerlist.add(worker);
		worker_table.setItems(workerlist);
	}
	
	public void Worker_Delete(ActionEvent event) {//删除会员
		workerlist.remove(selection);
		worker_table.refresh();
	}


	public void setNumber(String number) {
		workerNo.setText(number);
	}
	
	/*
	 * 
	 */

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setSystemTime();
		showMemberTable();
		showReportTable();
		showDepTable();
		setChoiceId();
		showWorkerTable();
	}
}
