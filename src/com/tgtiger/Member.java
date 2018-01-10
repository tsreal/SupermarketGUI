package com.tgtiger;

import javafx.beans.property.*;

public final class Member {
	private final SimpleStringProperty name = new SimpleStringProperty();
	private final SimpleStringProperty memberNo = new SimpleStringProperty();
	private final SimpleStringProperty phone = new SimpleStringProperty();
	private final SimpleDoubleProperty consumption = new SimpleDoubleProperty();
	private final SimpleStringProperty dateSignUp = new SimpleStringProperty();
	private final SimpleBooleanProperty isexpire = new SimpleBooleanProperty();//根据注册时间+1年判断

	public Member(String name, String memberNo, String phone, double consumption, String dateSignUp, boolean isexpire) {
		setName(name);
		setMemberNo(memberNo);
		setPhone(phone);
		setConsumption(consumption);
		setDateSignUp(dateSignUp);
		setIsexpire(isexpire);
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

	public SimpleStringProperty MemberNoProperty() {
		return memberNo;
	}

	public String getMemberNo() {
		return memberNo.get();
	}

	public void setMemberNo(String memberNo) {
		this.memberNo.set(memberNo);
	}

	public SimpleStringProperty PhoneProperty() {
		return memberNo;
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

	public SimpleDoubleProperty ConsumptionProperty() {
		return consumption;
	}

	public double getConsumption() {
		return consumption.get();
	}

	public void setConsumption(double consumption) {
		this.consumption.set(consumption);
	}
	
	public SimpleBooleanProperty IsexpireProperty(){
		return isexpire;
	}
	public boolean getIsexpire(){
		return isexpire.get();
	}
	public void setIsexpire(boolean isexpire){
		this.isexpire.set(isexpire);
	}

}
