package entities;

import java.io.Serializable;

public class BackOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private Customer customer;
	private Appliance appliance;
	private int backOrderQuantity;
	private static final String BACKORDER_STRING = "B";
	private static int idCounter;

	public BackOrder(Customer customer, Appliance appliance, int backOrderQuantity) {
		this.id = BACKORDER_STRING + ++idCounter;
		this.customer = customer;
		this.appliance = appliance;
		this.backOrderQuantity = backOrderQuantity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Appliance getAppliance() {
		return appliance;
	}

	public int getBackOrderQuantity() {
		return backOrderQuantity;
	}
}
