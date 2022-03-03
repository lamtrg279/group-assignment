package entities;

import java.io.Serializable;

public class ClothDryer extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
	private double repairCost;

	public ClothDryer(String brand, String model, double price, double repairCost) {
		super(brand, model, price);
		this.repairCost = repairCost;
	}

	public double getRepairCost() {
		return repairCost;
	}

	public void setRepairCost(double repairCost) {
		this.repairCost = repairCost;
	}
}
