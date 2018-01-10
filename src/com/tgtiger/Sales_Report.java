package com.tgtiger;

import javafx.beans.property.*;

public final class Sales_Report {
	private final SimpleStringProperty name = new SimpleStringProperty();
	private final SimpleIntegerProperty amount = new SimpleIntegerProperty();
	private final SimpleIntegerProperty ordinary_sales = new SimpleIntegerProperty();
	private final SimpleIntegerProperty member_sales = new SimpleIntegerProperty();
	private final SimpleDoubleProperty unitprice = new SimpleDoubleProperty();
	private final SimpleDoubleProperty subtotal = new SimpleDoubleProperty();

	public Sales_Report(String name, int amount, int ordinary_sales, int member_sales, double unitprice,
			double subtotal) {
		setName(name);
		setAmount(amount);
		setOrdinary_sales(ordinary_sales);
		setMember_sales(member_sales);
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

	public SimpleIntegerProperty Ordinary_salesProperty() {
		return ordinary_sales;
	}

	public int getOrdinary_sales() {
		return ordinary_sales.get();
	}

	public void setOrdinary_sales(int ordinary_sales) {
		this.ordinary_sales.set(ordinary_sales);
	}

	public SimpleIntegerProperty Member_salesProperty() {
		return member_sales;
	}

	public int getMember_sales() {
		return member_sales.get();
	}

	public void setMember_sales(int member_sales) {
		this.member_sales.set(member_sales);
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