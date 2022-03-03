package entities;

import java.io.Serializable;

public class ClothWasher extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
	private double repairCost;

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
