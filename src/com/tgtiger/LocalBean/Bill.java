package com.tgtiger.LocalBean;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public final class Bill {
	private final SimpleStringProperty name = new SimpleStringProperty();
	private final SimpleIntegerProperty amount = new SimpleIntegerProperty();
	private final SimpleDoubleProperty unitprice = new SimpleDoubleProperty();
	private final SimpleDoubleProperty subtotal = new SimpleDoubleProperty();

	public Bill(String name, int amount, double unitprice, double subtotal) {
		setName(name);
		setAmount(amount);
		setUnitprice(unitprice);
		setSubtotal(subtotal);
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

	public SimpleIntegerProperty AmountProperty() {
		return amount;
	}

	public int getAmount() {
		return amount.get();
	}

	public void setAmount(int amount) {
		this.amount.set(amount);
	}

	public SimpleDoubleProperty UnitpriceProperty() {
		return unitprice;
	}

	public double getUnitprice() {
		return unitprice.get();
	}

	public void setUnitprice(double unitprice) {
		this.unitprice.set(unitprice);
	}

	public SimpleDoubleProperty SubtotalProperty() {
		return subtotal;
	}

	public double getSubtotal() {
		return subtotal.get();
	}

	public void setSubtotal(double subtotal) {
		this.subtotal.set(subtotal);
	}

}

