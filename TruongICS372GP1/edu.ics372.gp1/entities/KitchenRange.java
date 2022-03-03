package entities;

import java.io.Serializable;

public class KitchenRange extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;

	public KitchenRange(String brand, String model, double price, int type) {
		super(brand, model, price, type);
	}
}
