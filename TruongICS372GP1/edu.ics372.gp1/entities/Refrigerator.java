package entities;

import java.io.Serializable;

/**
 * Represents a Refrigerator object in the Company
 */
public class Refrigerator extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
	private double capacity;

	/**
	 * Create a Refrigerator object
	 * 
	 * @param brand
	 * @param model
	 * @param price
	 * @param type
	 * @param capacity
	 */
	public Refrigerator(String brand, String model, double price, int type, double capacity) {
		super(brand, model, price, type);
		this.capacity = capacity;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

}
