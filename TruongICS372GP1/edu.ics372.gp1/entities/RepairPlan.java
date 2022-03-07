package entities;

import java.io.Serializable;

/**
 * Represents a Repair plan between a customer and an appliance
 */
public class RepairPlan implements Serializable {
	private static final long serialVersionUID = 1L;
	private Appliance appliance;
	private Customer customer;

	/**
	 * The customer and appliance are stored.
	 * 
	 * @param customer  Customer who applies for the repair plan
	 * @param appliance Appliance which has repair plan
	 */
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
