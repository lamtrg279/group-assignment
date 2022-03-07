package entities;

import java.io.Serializable;

/**
 * Represents a Back order between a customer and an appliance
 */
public class BackOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private Customer customer;
	private Appliance appliance;
	private int backOrderQuantity;
	private static final String BACKORDER_STRING = "B";
	private static int idCounter;

	/**
	 * The backorder is created with a customer, appliance, quantity
	 * 
	 * @param customer          Customer who has backorder
	 * @param appliance         Appliance with backorder
	 * @param backOrderQuantity quantity of appliance in the backorder
	 */
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
