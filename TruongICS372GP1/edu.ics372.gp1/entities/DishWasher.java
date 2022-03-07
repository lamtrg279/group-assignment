package entities;

import java.io.Serializable;

/**
 * Represents a DishWasher object in the Company
 */
public class DishWasher extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Create a DishWasher object
	 * 
	 * @param brand
	 * @param model
	 * @param price
	 * @param type
	 */
	public DishWasher(String brand, String model, double price, int type) {
		super(brand, model, price, type);
	}
}
