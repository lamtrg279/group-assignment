package entities;

import java.io.Serializable;

public class Refrigerator extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
	private double capacity;

	public Refrigerator(String brand, String model, double price, double capacity) {
		super(brand, model, price);
		this.capacity = capacity;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

}
