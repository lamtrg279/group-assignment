package facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

import collections.Catalog;
import collections.CustomerList;
import entities.Appliance;
import entities.ClothDryer;
import entities.ClothWasher;
import entities.Customer;
import entities.DishWasher;
import entities.Furnace;
import entities.KitchenRange;
import entities.Refrigerator;
import iterators.SafeApplianceIterator;
import iterators.SafeCustomerIterator;

/**
 * The face class handling all requests from users
 */
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;
	private Catalog catalog = Catalog.getInstance();
	private CustomerList customers = CustomerList.getInstance();
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
					request.getAppliancePrice(), request.getApplianceRepairCost());
		}
		if (request.getApplianceType() == 2) {
			appliance = new ClothDryer(request.getApplianceBrand(), request.getApplianceName(),
					request.getAppliancePrice(), request.getApplianceRepairCost());
		}
		if (request.getApplianceType() == 3) {
			appliance = new KitchenRange(request.getApplianceBrand(), request.getApplianceName(),
					request.getAppliancePrice());
		}
		if (request.getApplianceType() == 4) {
			appliance = new DishWasher(request.getApplianceBrand(), request.getApplianceName(),
					request.getAppliancePrice());
		}
		if (request.getApplianceType() == 5) {
			appliance = new Refrigerator(request.getApplianceBrand(), request.getApplianceName(),
					request.getAppliancePrice(), request.getApplianceCapacity());
		}
		if (request.getApplianceType() == 6) {
			appliance = new Furnace(request.getApplianceBrand(), request.getApplianceName(),
					request.getAppliancePrice(), request.getApplianceMaximumHeatingOutput());
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
}