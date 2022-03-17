package userinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

import facade.Company;
import facade.Request;
import facade.Result;

/**
 * 
 * This class implements the user interface for the Appliance project. The
 * commands are encoded as integers using a number of static final variables. A
 * number of utility methods exist to make it easier to parse the input.
 *
 */
public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Company company;
	private static final int EXIT = 0;
	private static final int ADD_SINGLE_MODEL = 1;
	private static final int ADD_CUSTOMER = 2;
	private static final int ADD_SINGLE_MODEL_INVENTORY = 3;
	private static final int PURCHASE = 4;
	private static final int FULFILL_BACKORDER = 5;
	private static final int ENROLL_IN_REPAIR_PLAN = 6;
	private static final int WITHDRAW_CUSTOMER_FROM_REPAIR_PLAN = 7;
	private static final int CHARGE_ALL_REPAIR_PLANS = 8;
	private static final int PRINT_ALL_REVENUE = 9;
	private static final int LIST_APPLIANCES = 10;
	private static final int LIST_ALL_USERS_IN_REPAIR_PLANS = 11;
	private static final int LIST_CUSTOMERS = 12;
	private static final int LIST_ALL_BACKORDERS = 13;
	private static final int SAVE = 14;
	private static final int HELP = 15;

	/**
	 * Made private for singleton pattern. Conditionally looks for any saved data.
	 * Otherwise, it gets a singleton Library object.
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and use it?")) {
			retrieve();
		} else {
			company = Company.instance();
		}
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException e) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 * 
	 * @param prompt The String to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * Converts the string to a number
	 * 
	 * @param prompt Whatever the user wants as prompt
	 * @return the input from the keyboard
	 */
	public String getName(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				return line;
			} catch (IOException e) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Converts the string to a number
	 * 
	 * @param prompt The string for prompting
	 * @return the integer corresponding to the string
	 */
	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				if (number.intValue() < 0) {
					throw new IllegalArgumentException("Please input a positive number");
				}
				return number.intValue();
			} catch (NumberFormatException e) {
				System.out.println("Please input a number ");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		} while (true);
	}

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("\nEnter command: " + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException e) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Display the help screen
	 */
	public void help() {
		System.out.println("Enter a number between 0 and 12 as explained below:");
		System.out.println(EXIT + " to Exit");
		System.out.println(ADD_SINGLE_MODEL + " to add a single model");
		System.out.println(ADD_CUSTOMER + " to add a single customer");
		System.out.println(ADD_SINGLE_MODEL_INVENTORY + " to add to inventory for a single model");
		System.out.println(PURCHASE + " to purchase one or more models for a single customer");
		System.out.println(FULFILL_BACKORDER + " to fullfill a single backorder");
		System.out.println(ENROLL_IN_REPAIR_PLAN + " to enroll a customer in a repair plan for a single appliance");
		System.out.println(
				WITHDRAW_CUSTOMER_FROM_REPAIR_PLAN + " to withdraw customer from a repair plan for a single appliance");
		System.out.println(CHARGE_ALL_REPAIR_PLANS + " to charge all repair plans");
		System.out.println(PRINT_ALL_REVENUE + " to print revenue from all sales and repair plans");
		System.out.println(LIST_APPLIANCES + " to list all or some types of appliances");
		System.out.println(LIST_ALL_USERS_IN_REPAIR_PLANS + " to list all users in repair plans");
		System.out.println(LIST_CUSTOMERS + " to list customers");
		System.out.println(LIST_ALL_BACKORDERS + " to list all backorders");
		System.out.println(SAVE + " to save data to disk");
		System.out.println(HELP + " for help");
	}

	/**
	 * Method to be called for adding an appliance. Prompts the user for the
	 * appropriate values and uses the appropriate Company method for adding the
	 * appliance.
	 */
	public void addAppliance() {
		Request.instance().setApplianceBrand(getName("Enter brand name"));
		Request.instance().setApplianceName(getName("Enter model name"));
		Request.instance().setAppliancePrice(getNumber("Enter price"));
		Result result = new Result();
		Request.instance().setApplianceType(getNumber(
				"Enter type of appliance: 1 for ClothWasher, 2 for ClothDryer, 3 for KitchenRange, 4 for DishWasher, 5 for Refrigerator, 6 for Furnace"));
		switch (Request.instance().getApplianceType()) {
		case 1:
			Request.instance().setApplianceRepairCost(getNumber("Enter repair plan cost for cloth washer"));
			result = company.addAppliance(Request.instance());
			break;
		case 2:
			Request.instance().setApplianceRepairCost(getNumber("Enter repair plan cost for cloth dryer"));
			result = company.addAppliance(Request.instance());
			break;
		case 5:
			Request.instance().setApplianceCapacity(getNumber("Enter capacity for refrigerator"));
			result = company.addAppliance(Request.instance());
			break;
		case 6:
			Request.instance().setApplianceMaximumHeatingOutput(getNumber("Enter maximum heating output for furnace"));
			result = company.addAppliance(Request.instance());
			break;
		default:
			result = company.addAppliance(Request.instance());
		}
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Appliance could not be added");
		} else {
			System.out.println(result.getApplianceName() + "'s id is " + result.getApplianceId());
		}
	}

	/**
	 * Method to be called for adding a customer. Prompts the user for the
	 * appropriate values and uses the appropriate Company method for adding the
	 * customer.
	 */
	public void addCustomer() {
		Request.instance().setCustomerName(getName("Enter customer name"));
		Request.instance().setCustomerAddress(getName("Enter customer address"));
		Request.instance().setCustomerPhone(getName("Enter customer phone"));
		Result result = company.addCustomer(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not add customer");
		} else {
			System.out.println(result.getCustomerName() + "'s id is " + result.getCustomerId());
		}
	}

	/**
	 * MEthod to be called for adding appliance in inventory.
	 */
	public void addInventory() {
		Request.instance().setApplianceId(getToken("Enter appliance ID"));
		Request.instance().setApplianceQuantity(getNumber("Enter quantity"));
		Result result = company.addInventory(Request.instance());
		switch (result.getResultCode()) {
		case Result.APPLIANCE_NOT_FOUND:
			System.out.println("No such appliance with id " + Request.instance().getApplianceId() + " in Company");
			break;
		case Result.OPERATION_COMPLETED:
			System.out.println("Inventory has been added to model ID: " + Request.instance().getApplianceId()
					+ " for quantity " + Request.instance().getApplianceQuantity());
			break;
		default:
			System.out.println("An error has occured");
			break;
		}
	}

	/**
	 * Method to be called for purchasing a model. Prompts the user for the
	 * appropriate values and uses the appropriate Company method
	 */
	public void purchaseModel() {
		Request.instance().setCustomerId(getToken("Enter customer ID"));
		Result result = company.searchCustomer(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("No such customer with id " + Request.instance().getCustomerId() + " in Company");
			return;
		}
		do {
			Request.instance().setApplianceId(getToken("Enter appliance ID"));
			Request.instance().setApplianceQuantity(getNumber("Enter quantity"));
			result = company.purchaseModel(Request.instance());
			switch (result.getResultCode()) {
			case Result.APPLIANCE_NOT_FOUND:
				System.out.println("No such appliance with id " + Request.instance().getApplianceId() + " in Company");
				break;
			case Result.OPERATION_COMPLETED:
				System.out.println("Purchase by customer " + result.getCustomerName() + " for appliance "
						+ result.getApplianceName() + " is completed");
				break;
			default:
				System.out.println("An error has occured");
			}
			if (result.getBackorderId() != null) {
				System.out.println("Backorder id is " + result.getBackorderId());
			}
		} while (yesOrNo("Purchase more appliance? "));
	}

	/**
	 * Method to be called for enroll customers into repair plan. Prompts the user
	 * for the appropriate values and uses the appropriate Company method for
	 * enrolling customers
	 */
	public void enrollInRepairPlan() {
		Request.instance().setCustomerId(getToken("Enter customer ID"));
		Result result = company.searchCustomer(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("No such customer with id " + Request.instance().getCustomerId() + " in Company");
			return;
		}
		Request.instance().setApplianceId(getToken("Enter appliance ID"));
		result = company.enrollRepairPlan(Request.instance());
		switch (result.getResultCode()) {
		case Result.APPLIANCE_NOT_FOUND:
			System.out.println("No such appliance with id " + Request.instance().getApplianceId() + " in Company");
			break;
		case Result.APPLIANCE_NO_REPAIR_PLAN:
			System.out.println(
					"Appliance with id " + Request.instance().getApplianceId() + " doesn't have a repair plan");
			break;
		case Result.OPERATION_COMPLETED:
			System.out.println("A repair plan has been placed for customer " + result.getCustomerName()
					+ " for appliance " + result.getApplianceName());
			break;
		default:
			System.out.println("An error has occured");

		}
	}

	/**
	 * Method to be called for removing a customers from a single appliance repair
	 * plan. Prompts the user for the appropriate values and uses the appropriate
	 * Company method to remove customers from single plan
	 */
	public void removeCustomerFromPlan() {
		Request.instance().setCustomerId(getToken("Enter customer ID"));
		Request.instance().setApplianceId(getToken("Enter appliance ID"));
		Result result = company.removeSingleRepairPlan(Request.instance());
		switch (result.getResultCode()) {
		case Result.APPLIANCE_NOT_FOUND:
			System.out.println("No such appliance with id " + Request.instance().getApplianceId() + " in Company");
			break;
		case Result.CUSTOMER_NOT_FOUND:
			System.out.println("No such customer with id " + Request.instance().getCustomerId() + " in Company");
			break;
		case Result.APPLIANCE_NO_REPAIR_PLAN:
			System.out.println(
					"Appliance with id " + Request.instance().getApplianceId() + " doesn't have a repair plan");
			break;
		case Result.OPERATION_COMPLETED:
			System.out.println("A repair plan was removed for customer " + result.getCustomerName() + " for appliance "
					+ result.getApplianceName());
			break;
		case Result.OPERATION_FAILED:
			System.out.println("Couldn't find a customer associated with that appliance");
			break;
		default:
			System.out.println("An error has occured");

		}

	}

	/**
	 * Method to be called for charging all repair plans. Uses the appropriate
	 * Company method for charing all repair plans
	 */
	public void chargeAllRepairPlans() {
		Iterator<Result> iterator = company.getUsersInRepairPlans();
		while (iterator.hasNext()) {
			Result result = iterator.next();
			company.updateRepairPlanRevenue(result.getApplianceRepairCost());
		}
		System.out.println("Charged For All Repair Plans");
	}

	/**
	 * Method to be called for print sales and repair plan revenue.
	 */
	public void printRevenueAll() {
		System.out.println("Sales Revenue: " + company.getSalesRevenue());
		System.out.println("Repair Plans Revenue: " + company.getRepairPlanRevenue());
	}

	/**
	 * Get and print all or some appliances
	 */
	public void getAppliances() {
		Iterator<Result> iterator = company.getAppliances();
		Request.instance().setApplianceType(getNumber(
				"Enter type of appliance: 0 for all appliances, 1 for ClothWasher, 2 for ClothDryer, 3 for KitchenRange, 4 for DishWasher, 5 for Refrigerator, 6 for Furnace"));
		System.out.println("List of appliances (brand, name, id, price, quantity)");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			if (Request.instance().getApplianceType() == 0) {
				System.out.println(
						result.getApplianceBrand() + " " + result.getApplianceName() + " " + result.getApplianceId()
								+ " " + result.getAppliancePrice() + " " + result.getApplianceQuantity());
			} else {
				if (result.getApplianceType() == Request.instance().getApplianceType()) {
					System.out.println(
							result.getApplianceBrand() + " " + result.getApplianceName() + " " + result.getApplianceId()
									+ " " + result.getAppliancePrice() + " " + result.getApplianceQuantity());
				}
			}
		}
		System.out.println("End of listing");
	}

	/**
	 * Display customer's info and appliance's info in repair plan
	 */
	public void getUsersInRepairPlans() {
		Iterator<Result> iterator = company.getUsersInRepairPlans();
		System.out.println("List of customers (name, address, phone, id, brand, model, monthly cost)");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getCustomerName() + ", " + result.getCustomerAddress() + ", "
					+ result.getCustomerPhone() + ", " + result.getCustomerId() + ", " + result.getApplianceBrand()
					+ ", " + result.getApplianceName() + ", " + result.getApplianceRepairCost());
		}
		System.out.println("End of listing");
	}

	/**
	 * Display all customers
	 */
	public void getCustomers() {
		Iterator<Result> iterator = company.getCustomers();
		System.out.println("List of customers (name, address, phone, id)");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getCustomerName() + ", " + result.getCustomerAddress() + ", "
					+ result.getCustomerPhone() + ", " + result.getCustomerId());
		}
		System.out.println("End of listing");
	}

	/**
	 * Display customer's info and appliance's info in back order
	 */
	public void getBackOrders() {
		Iterator<Result> iterator = company.getBackOrders();
		System.out.println(
				"List of Backorders (Customer Name, Customer ID, Appliance brand, Appliance model and quantity)");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(
					result.getCustomerName() + ", " + result.getCustomerId() + ", " + result.getApplianceBrand() + ", "
							+ result.getApplianceName() + ", " + result.getApplianceQuantity());
		}
		System.out.println("End of List");

	}

	/**
	 * Method to be called for saving the Company object. Uses the appropriate
	 * Company method for saving.
	 * 
	 */
	private void save() {
		if (company.save()) {
			System.out.println("The companay has been successfully saved in the file CompanyData \n");
		} else {
			System.out.println("There has been an error in saving\n");
		}
	}

	/**
	 * Method to be called for retrieving saved data. Uses the appropriate Company
	 * method for retrieval.
	 */
	private void retrieve() {
		try {
			if (company == null) {
				company = Company.retrieve();
				if (company != null) {
					System.out.println("The company has been successfully retrieved from the file Companydata\n");
				} else {
					System.out.println("File doesn't exist; creating new company");
					company = Company.instance();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Orchestrate the whole process. Calls the appropriate method for the different
	 * functionalities.
	 */
	public void process() {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_SINGLE_MODEL:
				addAppliance();
				break;
			case ADD_CUSTOMER:
				addCustomer();
				break;
			case ADD_SINGLE_MODEL_INVENTORY:
				addInventory();
				break;
			case PURCHASE:
				purchaseModel();
				break;
			case FULFILL_BACKORDER:
				// method for fulfilling backorder
				break;
			case ENROLL_IN_REPAIR_PLAN:
				enrollInRepairPlan();
				break;
			case WITHDRAW_CUSTOMER_FROM_REPAIR_PLAN:
				removeCustomerFromPlan();
				break;
			case CHARGE_ALL_REPAIR_PLANS:
				chargeAllRepairPlans();
				break;
			case PRINT_ALL_REVENUE:
				printRevenueAll();
				break;
			case LIST_APPLIANCES:
				getAppliances();
				break;
			case LIST_ALL_USERS_IN_REPAIR_PLANS:
				getUsersInRepairPlans();
				break;
			case LIST_CUSTOMERS:
				getCustomers();
				break;
			case LIST_ALL_BACKORDERS:
				getBackOrders();
				break;
			case SAVE:
				save();
				break;
			case HELP:
				help();
				break;
			}
		}
	}

	/**
	 * The method to start the application. Simply calls process()
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}