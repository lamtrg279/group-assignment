package tests;

import java.util.Iterator;

import entities.Appliance;
import entities.Customer;
import facade.Company;
import facade.Request;
import facade.Result;

/**
 * This class generates sample automated tests for the library system using
 * asserts
 */
public class AutomatedTester {
	private Company company;
	private String[] names = { "n1", "n2", "n3", "n4", "n5" };
	private String[] addresses = { "a1", "a2", "a3", "a4", "a5" };
	private String[] phones = { "p1", "p2", "p3", "p4", "p5" };
	private String[] customerID = { "C1", "C2", "C3", "C4", "C5" };
	private Customer[] customers = new Customer[5];
	private String[] applianceBrand = { "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9", "b10", "b11", "b12",
			"b13", "b14", "b15" };
	private String[] applianceModel = { "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10", "a11", "a12",
			"a13", "a14", "a15" };
	private String[] applianceID = { "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12", "A13",
			"A14", "A15" };
	private String[] applianceWithRepairPlanID = { "A1", "A2", "A7", "A8", "A13" };
	private double[] price = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0 };
	private int[] applianceType = { 1, 2, 3, 4, 5, 6 };
	private Appliance[] appliance = new Appliance[15];
	private int[] quantity = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
	private String[] backOrderID = { "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8" };

	/**
	 * Test Customer creation
	 */
	public void testAddCustomer() {
		for (int count = 0; count < customers.length; count++) {
			Request.instance().setCustomerAddress(addresses[count]);
			Request.instance().setCustomerName(names[count]);
			Request.instance().setCustomerPhone(phones[count]);
			Result result = Company.instance().addCustomer(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getCustomerName().equals(names[count]);
			assert result.getCustomerPhone().equals(phones[count]);
		}
	}

	/**
	 * Test Appliance creation
	 */
	public void testAddAppliance() {
		for (int count = 0; count < appliance.length; count++) {
			Request.instance().setApplianceBrand(applianceBrand[count]);
			Request.instance().setApplianceName(applianceModel[count]);
			Request.instance().setAppliancePrice(price[count]);
			Request.instance().setApplianceType(applianceType[count % 6]);
			if (applianceType[count % 6] == 1 || applianceType[count % 6] == 2) {
				Request.instance().setApplianceRepairCost(100);
			}
			Result result = Company.instance().addAppliance(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getApplianceBrand().equals(applianceBrand[count]);
			assert (result.getAppliancePrice() == price[count]);
		}
	}

	/**
	 * Test adding to inventory
	 */
	public void testAddInventory() {
		for (int count = 0; count < appliance.length; count++) {
			Request.instance().setApplianceId(applianceID[count]);
			Request.instance().setApplianceQuantity(4);
			Result result = Company.instance().addInventory(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert (result.getApplianceQuantity() == 5);
		}
	}

	/**
	 * Test purchase
	 */
	public void testPurchase() {
		for (int count = 0; count < appliance.length; count++) {
			Request.instance().setCustomerId(customerID[count % 5]);
			Request.instance().setApplianceId(applianceID[count]);
			Request.instance().setApplianceQuantity(quantity[count]);
			Result result = Company.instance().purchaseModel(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
		}
		assert (670.00 == Company.instance().getSalesRevenue());
	}

	/**
	 * test fulfill back-order
	 */
	public void testFulfillBackOrder() {
		for (int count = 0; count < backOrderID.length; count++) {
			Request.instance().setBackorderId(backOrderID[count]);
			Result result = Company.instance().fulfillBackOrder(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getBackorderId().equals(backOrderID[count]);
		}
	}

	/**
	 * Test enroll customer in a repair plan
	 */
	public void testEnrollInRepairPlan() {
		for (int count = 0; count < customers.length; count++) {
			Request.instance().setCustomerId(customerID[count]);
			Request.instance().setApplianceId(applianceWithRepairPlanID[count]);
			Result result = Company.instance().enrollRepairPlan(Request.instance());
			assert result.getApplianceId().equals(applianceWithRepairPlanID[count]);
			assert result.getCustomerId().equals(customerID[count]);
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
		}
	}

	/**
	 * Test remove a customer in a repair plan
	 */
	public void testRemoveSingleRepairPlan() {
		for (int count = 0; count < customers.length; count++) {
			Request.instance().setCustomerId(customerID[count]);
			Request.instance().setApplianceId(applianceWithRepairPlanID[count]);
			Result result = Company.instance().removeSingleRepairPlan(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
		}
	}

	/**
	 * Test charge all repair plans
	 */
	public void testChargeAllRepairPlans() {
		Iterator<Result> iterator = Company.instance().getUsersInRepairPlans();
		while (iterator.hasNext()) {
			Result result = iterator.next();
			Company.instance().updateRepairPlanRevenue(result.getApplianceRepairCost());
		}
		assert (500.00 == Company.instance().getRepairPlanRevenue());
	}

	public void testPrintRevenueAll() {
		assert (1240.00 == Company.instance().getSalesRevenue());
		assert (500.00 == Company.instance().getRepairPlanRevenue());
	}

	/**
	 * Test all
	 */
	public void testAll() {
		testAddCustomer();
		testAddAppliance();
		testAddInventory();
		testPurchase();
		testFulfillBackOrder();
		testEnrollInRepairPlan();
		testChargeAllRepairPlans();
		testRemoveSingleRepairPlan();
		testPrintRevenueAll();
	}

	public static void main(String[] args) {
		new AutomatedTester().testAll();
	}
}