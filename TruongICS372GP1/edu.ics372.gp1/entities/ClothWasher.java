package entities;

import java.io.Serializable;

/**
 * Represents a ClothWasher object in the Company
 */
public class ClothWasher extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
	private double repairCost;

	/**
	 * Create a cloth washer object with given band, model, price, type, repairCost
	 * 
	 * @param brand
	 * @param model
	 * @param price
	 * @param type
	 * @param repairCost
	 */
	public ClothWasher(String brand, String model, double price, int type, double repairCost) {
		super(brand, model, price, type);
		this.repairCost = repairCost;
	}

	public double getRepairCost() {
		return repairCost;
	}

	public void setRepairCost(double repairCost) {
		this.repairCost = repairCost;
	}

}
