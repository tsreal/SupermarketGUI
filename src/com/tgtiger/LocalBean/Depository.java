package com.tgtiger.LocalBean;

import javafx.beans.property.*;

public final class Depository {
	private final SimpleStringProperty name = new SimpleStringProperty();
	private final SimpleIntegerProperty amount = new SimpleIntegerProperty();
	private final SimpleIntegerProperty importNo = new SimpleIntegerProperty();
	private final SimpleStringProperty importDate = new SimpleStringProperty();
	public Depository(String name, int amount, int importNo, String  importDate) {
		setName(name);
		setAmount(amount);
		setImportNo(importNo);
		setImportDate(importDate);
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

	public SimpleIntegerProperty ImprotNoProperty() {
		return importNo;
	}

	public int getImportNo() {
		return importNo.get();
	}

	public void setImportNo(int importNo) {
		this.importNo.set(importNo);
	}

	public SimpleStringProperty ImportDateProperty() {
		return importDate;
	}

	public String getImportDate() {
		return importDate.get();
	}

	public void setImportDate(String importDate) {
		this.importDate.set(importDate);
	}

	public SimpleIntegerProperty AmountProperty() {
		return amount;
	}

	public int getAmount() {
		return amount.get();
	}

	public void setAmount(int amount) {
		this.amount.set(amount);
	}
}