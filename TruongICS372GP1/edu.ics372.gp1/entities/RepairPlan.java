package entities;

import java.io.Serializable;

public class RepairPlan implements Serializable {
	private static final long serialVersionUID = 1L;
	private Appliance appliance;
	private Customer customer;

	public RepairPlan(Customer customer, Appliance appliance) {
		this.customer = customer;
		this.appliance = appliance;
	}

	public Appliance getAppliance() {
		return appliance;
	}

	public void setAppliance(Appliance appliance) {
		this.appliance = appliance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
