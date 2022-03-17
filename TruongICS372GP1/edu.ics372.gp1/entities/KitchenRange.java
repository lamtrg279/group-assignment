package entities;

import java.io.Serializable;

/**
 * Represents a KitchenRange object in the Company
 */
public class KitchenRange extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Create a KitchenRange object
	 * 
	 * @param brand
	 * @param model
	 * @param price
	 * @param type
	 */
	public KitchenRange(String brand, String model, double price, int type) {
		super(brand, model, price, type);
	}

	@Override
	public String toString() {
		return "KitchenRange [toString()=" + super.toString() + "]";
	}
}
