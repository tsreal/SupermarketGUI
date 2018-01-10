package com.tgtiger.LocalBean;

import javafx.beans.property.*;

public final class Worker {
	private final SimpleStringProperty name = new SimpleStringProperty();
	private final SimpleStringProperty workerNo = new SimpleStringProperty();
	private final SimpleStringProperty phone = new SimpleStringProperty();
	private final SimpleStringProperty dateSignUp = new SimpleStringProperty();
	private final SimpleIntegerProperty level = new SimpleIntegerProperty();


	public Worker(String name, String workerNo, String phone, String dateSignUp, int level) {
		setName(name);
		setWorkerNo(workerNo);
		setPhone(phone);
		setDateSignUp(dateSignUp);		
		setLevel(level);
	}

	public SimpleStringProperty NameProperty() {
		return name;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}
	
	public SimpleStringProperty WorkerNoProperty() {
		return workerNo;
	}

	public String getWorkerNo() {
		return workerNo.get();
	}

	public void setWorkerNo(String workerNo) {
		this.workerNo.set(workerNo);
	}
	
	public SimpleStringProperty PhoneProperty() {
		return phone;
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone.set(phone);
	}
	
	public SimpleStringProperty DateSignUpProperty() {
		return dateSignUp;
	}

	public String getDateSignUp() {
		return dateSignUp.get();
	}

	public void setDateSignUp(String dateSignUp) {
		this.dateSignUp.set(dateSignUp);
	}
	


	public SimpleIntegerProperty LevelProperty() {
		return level;
	}

	public int getLevel() {
		return level.get();
	}

	public void setLevel(int level) {
		this.level.set(level);
	}

}