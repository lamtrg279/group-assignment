package entities;

import java.io.Serializable;

public class Furnace extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
	private double maximumHeatOutput;

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
