package entities;

import java.io.Serializable;

public class DishWasher extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;

	public DishWasher(String brand, String model, double price, int type) {
		super(brand, model, price, type);
	}
}
