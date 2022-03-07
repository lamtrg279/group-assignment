package entities;

import java.io.Serializable;

/**
 * Represents a Furnace object in the Company
 */
public class Furnace extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
	private double maximumHeatOutput;

	/**
	 * Create a Furnace object
	 * 
	 * @param brand
	 * @param model
	 * @param price
	 * @param type
	 * @param maximumHeatOutput
	 */
	public Furnace(String brand, String model, double price, int type, double maximumHeatOutput) {
		super(brand, model, price, type);
		this.maximumHeatOutput = maximumHeatOutput;
	}

	public double getMaximumHeatOutput() {
		return maximumHeatOutput;
	}

	public void setMaximumHeatOutput(double maximumHeatOutput) {
		this.maximumHeatOutput = maximumHeatOutput;
	}
}
