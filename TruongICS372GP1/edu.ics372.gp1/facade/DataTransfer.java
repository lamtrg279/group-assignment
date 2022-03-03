package facade;

import entities.Appliance;
import entities.ClothDryer;
import entities.ClothWasher;
import entities.Customer;
import entities.Furnace;
import entities.Refrigerator;
import entities.RepairPlan;

/**
 * The DataTransfer class is used to facilitate data transfer between Company
 * and UserInterface. It is also used to support iterating over Customer and
 * Appliance objects. The class stores copies of fields that may be sent in
 * either direction.
 */
public abstract class DataTransfer {
	private String customerId;
	private String customerName;
	private String customerAddress;
	private String customerPhone;
	private String applianceId;
	private String applianceBrand;
	private String applianceName;
	private int applianceType;
	private double appliancePrice;
	private int applianceQuantity;
	private double applianceRepairCost;
	private double applianceCapacity;
	private double applianceMaximumHeatingOutput;

	/**
	 * This sets all fields to "none"
	 */
	public DataTransfer() {
		reset();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getApplianceId() {
		return applianceId;
	}

	public void setApplianceId(String applianceId) {
		this.applianceId = applianceId;
	}

	public String getApplianceBrand() {
		return applianceBrand;
	}

	public void setApplianceBrand(String applianceBrand) {
		this.applianceBrand = applianceBrand;
	}

	public String getApplianceName() {
		return applianceName;
	}

	public void setApplianceName(String applianceName) {
		this.applianceName = applianceName;
	}

	public double getAppliancePrice() {
		return appliancePrice;
	}

	public void setAppliancePrice(double appliancePrice) {
		this.appliancePrice = appliancePrice;
	}

	public int getApplianceQuantity() {
		return applianceQuantity;
	}

	public void setApplianceQuantity(int applianceQuantity) {
		this.applianceQuantity = applianceQuantity;
	}

	public double getApplianceRepairCost() {
		return applianceRepairCost;
	}

	public int getApplianceType() {
		return applianceType;
	}

	public void setApplianceType(int applianceType) {
		this.applianceType = applianceType;
	}

	public void setApplianceRepairCost(double applianceRepairCost) {
		this.applianceRepairCost = applianceRepairCost;
	}

	public double getApplianceCapacity() {
		return applianceCapacity;
	}

	public void setApplianceCapacity(double applianceCapacity) {
		this.applianceCapacity = applianceCapacity;
	}

	public double getApplianceMaximumHeatingOutput() {
		return applianceMaximumHeatingOutput;
	}

	public void setApplianceMaximumHeatingOutput(double applianceMaximumHeatingOutput) {
		this.applianceMaximumHeatingOutput = applianceMaximumHeatingOutput;
	}

	/**
	 * Sets all the appliance-related field using the Appliance parameter
	 * 
	 * @param appliance The appliance whose fields should be copied
	 */
	public void setApplianceField(Appliance appliance) {
		applianceId = appliance.getId();
		applianceBrand = appliance.getBrand();
		applianceName = appliance.getModel();
		appliancePrice = appliance.getPrice();
		applianceQuantity = appliance.getQuantity();
		applianceType = appliance.getType();
		if (appliance instanceof ClothWasher) {
			applianceRepairCost = ((ClothWasher) appliance).getRepairCost();
		}
		if (appliance instanceof ClothDryer) {
			applianceRepairCost = ((ClothDryer) appliance).getRepairCost();
		}
		if (appliance instanceof Refrigerator) {
			applianceCapacity = ((Refrigerator) appliance).getCapacity();
		}
		if (appliance instanceof Furnace) {
			applianceMaximumHeatingOutput = ((Furnace) appliance).getMaximumHeatOutput();
		}
	}

	/**
	 * Sets all the customer-related fields using the Customer parameter
	 * 
	 * @param customer The customer fields should be copied
	 */
	public void setCustomerField(Customer customer) {
		customerId = customer.getId();
		customerName = customer.getName();
		customerPhone = customer.getPhone();
		customerAddress = customer.getAddress();
	}

	public void setRepairPlanField(RepairPlan repairPlan) {
		Customer customer = repairPlan.getCustomer();
		customerId = customer.getId();
		customerName = customer.getName();
		customerPhone = customer.getPhone();
		customerAddress = customer.getAddress();
		Appliance appliance = repairPlan.getAppliance();
		applianceId = appliance.getId();
		applianceBrand = appliance.getBrand();
		applianceName = appliance.getModel();
		appliancePrice = appliance.getPrice();
		applianceQuantity = appliance.getQuantity();
		applianceType = appliance.getType();
		if (appliance instanceof ClothWasher) {
			applianceRepairCost = ((ClothWasher) appliance).getRepairCost();
		}
		if (appliance instanceof ClothDryer) {
			applianceRepairCost = ((ClothDryer) appliance).getRepairCost();
		}
		if (appliance instanceof Refrigerator) {
			applianceCapacity = ((Refrigerator) appliance).getCapacity();
		}
		if (appliance instanceof Furnace) {
			applianceMaximumHeatingOutput = ((Furnace) appliance).getMaximumHeatOutput();
		}

	}

	/**
	 * Sets all String fields to "none"
	 */
	public void reset() {
		customerId = "Invalid customer id";
		customerName = "No such customer";
		customerAddress = "No such customer";
		customerPhone = "No such customer";
		applianceId = "Invalid appliance id";
		applianceBrand = "No such appliance";
		applianceName = "No such appliance";
	}

}
