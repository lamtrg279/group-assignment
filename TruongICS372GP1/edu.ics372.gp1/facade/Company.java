package facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

import collections.BackOrderList;
import collections.Catalog;
import collections.CustomerList;
import collections.RepairPlanList;
import entities.Appliance;
import entities.BackOrder;
import entities.ClothDryer;
import entities.ClothWasher;
import entities.Customer;
import entities.DishWasher;
import entities.Furnace;
import entities.KitchenRange;
import entities.Refrigerator;
import entities.RepairPlan;
import iterators.SafeApplianceIterator;
import iterators.SafeCustomerIterator;
import iterators.SafeRepairPlanIterator;

/**
 * The face class handling all requests from users
 */
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;
	private Catalog catalog = Catalog.getInstance();
	private CustomerList customers = CustomerList.getInstance();
	private RepairPlanList repairPlans = RepairPlanList.getInstance();
	private BackOrderList backorders = BackOrderList.getInstance();
	private double salesRevenue = 0;
	private double repairPlanRevenue = 0;
	private static Company company;

	/**
	 * Private for the singleton pattern Create the catalog and customer collection
	 * objects
	 */
	private Company() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static Company instance() {
		if (company == null) {
			company = new Company();
		}
		return company;
	}

	/**
	 * Organizes the operations for adding an appliance
	 * 
	 * @param brand      Appliance brand
	 * @param model      Appliance model
	 * @param price      Appliance price
	 * @param repairCost Appliance repair cost
	 * @param capacity   Appliance capacity
	 * @param heatOutput Appliance heat output
	 * @return the Appliance object created
	 */
	public Result addAppliance(Request request) {
		Result result = new Result();
		Appliance appliance = new Appliance();
		if (request.getApplianceType() == 1) {
			appliance = new ClothWasher(request.getApplianceBrand(), request.getApplianceName(),
					request.getAppliancePrice(), request.getApplianceType(), request.getApplianceRepairCost());
		}
		if (request.getApplianceType() == 2) {
			appliance = new ClothDryer(request.getApplianceBrand(), request.getApplianceName(),
					request.getAppliancePrice(), request.getApplianceType(), request.getApplianceRepairCost());
		}
		if (request.getApplianceType() == 3) {
			appliance = new KitchenRange(request.getApplianceBrand(), request.getApplianceName(),
					request.getAppliancePrice(), request.getApplianceType());
		}
		if (request.getApplianceType() == 4) {
			appliance = new DishWasher(request.getApplianceBrand(), request.getApplianceName(),
					request.getAppliancePrice(), request.getApplianceType());
		}
		if (request.getApplianceType() == 5) {
			appliance = new Refrigerator(request.getApplianceBrand(), request.getApplianceName(),
					request.getAppliancePrice(), request.getApplianceType(), request.getApplianceCapacity());
		}
		if (request.getApplianceType() == 6) {
			appliance = new Furnace(request.getApplianceBrand(), request.getApplianceName(),
					request.getAppliancePrice(), request.getApplianceType(),
					request.getApplianceMaximumHeatingOutput());
		}
		if (catalog.insertAppliance(appliance)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setApplianceField(appliance);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Organizes the operations for adding a member
	 * 
	 * @param applianceId Appliance ID
	 * @return indication of the outcome
	 */
	public Result addInventory(Request request) {
		Result result = new Result();
		Appliance appliance = catalog.search(request.getApplianceId());
		if (appliance == null) {
			result.setResultCode(Result.APPLIANCE_NOT_FOUND);
			return result;
		}
		appliance.updateQuantity(request.getApplianceQuantity());
		result.setResultCode(Result.OPERATION_COMPLETED);
		return result;
	}

	/**
	 * Organizes the operations for adding a member
	 * 
	 * @param name    Customer name
	 * @param address Customer address
	 * @param phone   Customer phone
	 * @return the Customer object created
	 */
	public Result addCustomer(Request request) {
		Result result = new Result();
		Customer customer = new Customer(request.getCustomerName(), request.getCustomerAddress(),
				request.getCustomerPhone());
		if (customers.insertCustomer(customer)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setCustomerField(customer);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Organizes the operation for purchase an appliance
	 * 
	 * @param customerId  customer's id
	 * @param applianceId appliance's id
	 * @return indication of the outcome
	 */
	public Result purchaseModel(Request request) {
		Result result = new Result();
		Customer customer = customers.search(request.getCustomerId());
		if (customer == null) {
			result.setResultCode(Result.CUSTOMER_NOT_FOUND);
			return result;
		}
		result.setCustomerField(customer);
		Appliance appliance = catalog.search(request.getApplianceId());
		if (appliance == null) {
			result.setResultCode(Result.APPLIANCE_NOT_FOUND);
			return result;
		}
		result.setApplianceField(appliance);
		if (appliance.getQuantity() >= request.getApplianceQuantity()) {
			appliance.setQuantity(appliance.getQuantity() - request.getApplianceQuantity());
			company.updateSalesRevenue(appliance.getPrice() * request.getApplianceQuantity());
		} else if (appliance instanceof Furnace) {
			company.updateSalesRevenue(appliance.getPrice() * request.getApplianceQuantity());
			appliance.setQuantity(0);
		} else {
			company.updateSalesRevenue(appliance.getPrice() * appliance.getQuantity());
			BackOrder backorder = new BackOrder(customer, appliance,
					request.getApplianceQuantity() - appliance.getQuantity());
			appliance.setQuantity(0);
			result.setBackorderId(backorder.getId());
			backorders.insertBackorder(backorder);
		}
		result.setResultCode(Result.OPERATION_COMPLETED);
		return result;
	}

	/**
	 * Organizes the operation for enrolling a customer in a repair plan
	 * 
	 * @param customerId  customer's id
	 * @param applianceId appliance's id
	 * @return indication on the outcome
	 */
	public Result enrollRepairPlan(Request request) {
		Result result = new Result();
		Customer customer = customers.search(request.getCustomerId());
		if (customer == null) {
			result.setResultCode(Result.CUSTOMER_NOT_FOUND);
			return result;
		}
		result.setCustomerField(customer);
		Appliance appliance = catalog.search(request.getApplianceId());
		if (appliance == null) {
			result.setResultCode(Result.APPLIANCE_NOT_FOUND);
			return result;
		}
		if (appliance.getType() != 1 && appliance.getType() != 2) {
			result.setResultCode(Result.APPLIANCE_NO_REPAIR_PLAN);
			return result;
		}
		result.setApplianceField(appliance);
		RepairPlan repairPlan = new RepairPlan(customer, appliance);
		repairPlans.addRepairPlan(repairPlan);
		result.setResultCode(Result.OPERATION_COMPLETED);
		return result;
	}

	/**
	 * Search a given customer
	 * 
	 * @param customerId id of the customer
	 * @return true iff the customer is in the customerlist
	 */
	public Result searchCustomer(Request request) {
		Result result = new Result();
		Customer customer = customers.search(request.getCustomerId());
		if (customer == null) {
			result.setResultCode(Result.CUSTOMER_NOT_FOUND);
		} else {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setCustomerField(customer);
		}
		return result;
	}

	/**
	 * Retrieves a deserialized version of the library from disk
	 * 
	 * @return a Library object
	 */
	public static Company retrieve() {
		try {
			FileInputStream file = new FileInputStream("CompanyData");
			ObjectInputStream input = new ObjectInputStream(file);
			company = (Company) input.readObject();
			Customer.retrieve(input);
			Appliance.retrieve(input);
			return company;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	/**
	 * Serializes the Company object
	 * 
	 * @return true if the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("CompanyData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(company);
			Customer.save(output);
			Appliance.save(output);
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	public Iterator<Result> getCustomers() {
		return new SafeCustomerIterator(customers.iterator());
	}

	public Iterator<Result> getAppliances() {
		return new SafeApplianceIterator(catalog.iterator());
	}

	public Iterator<Result> getUsersInRepairPlans() {
		return new SafeRepairPlanIterator(repairPlans.iterator());
	}

	public RepairPlanList getRepairPlans() {
		return repairPlans;
	}

	public void setRepairPlans(RepairPlanList repairPlans) {
		this.repairPlans = repairPlans;
	}

	public double getSalesRevenue() {
		return salesRevenue;
	}

	public void updateSalesRevenue(double salesRevenue) {
		this.salesRevenue += salesRevenue;
	}

	public double getRepairPlanRevenue() {
		return repairPlanRevenue;
	}

	public void updateRepairPlanRevenue(double repairPlanRevenue) {
		this.repairPlanRevenue += repairPlanRevenue;
	}

}