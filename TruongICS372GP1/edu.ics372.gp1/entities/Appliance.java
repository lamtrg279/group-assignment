package entities;

import java.io.Serializable;

/**
 * Appliance represents an appliance of the Company
 */
public class Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
	private String brand;
	private String model;
	private String id;
	private double price;
	private int quantity;
	private static final String APPLIANCE_STRING = "A";
	private static int idCounter;

	/**
	 * Creates a single appliance
	 * 
	 * @param brand                      brand of the appliance
	 * @param model                      model of the appliance
	 * @param price                      price of the appliance
	 * @param washerAndDryerRepairCost   repair cost of appliance
	 * @param refrigeratorCapacity       capacity of appliance
	 * @param furnaceMaximumHeatingOuput heat output of appliance
	 */
	public Appliance(String brand, String model, double price) {
		this.brand = brand;
		this.model = model;
		this.id = APPLIANCE_STRING + ++idCounter;
		this.price = price;
		this.quantity = 1;
	}

	public Appliance() {
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Appliance other = (Appliance) object;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
