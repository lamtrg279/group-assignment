package entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	private int type;
	private static final String APPLIANCE_STRING = "A";
	private static int idCounter;

	/**
	 * Creates a single appliance
	 * 
	 * @param brand brand of the appliance
	 * @param model model of the appliance
	 * @param price price of the appliance
	 * @param type  type of the appliance
	 */
	public Appliance(String brand, String model, double price, int type) {
		this.brand = brand;
		this.model = model;
		this.id = APPLIANCE_STRING + ++idCounter;
		this.price = price;
		this.type = type;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(idCounter);
	}

	public static void retrieve(ObjectInputStream input) throws ClassNotFoundException, IOException {
		idCounter = (int) input.readObject();
	}

}
