package entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(idCounter);
	}

	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		idCounter = (int) input.readObject();
	}

	@Override
	public String toString() {
		return "BackOrder [id=" + id + ", customer=" + customer + ", appliance=" + appliance + ", backOrderQuantity="
				+ backOrderQuantity + "]";
	}
}
