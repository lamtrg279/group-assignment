package collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import entities.Customer;

/**
 * The collection class for Customer objects
 */
public class CustomerList implements Iterable<Customer>, Serializable {
	private static final long serialVersionUID = 1L;
	private List<Customer> customers = new LinkedList<Customer>();
	private static CustomerList customerList;

	private CustomerList() {
	}

	public static CustomerList getInstance() {
		if (customerList == null) {
			customerList = new CustomerList();
		}
		return customerList;
	}

	/**
	 * Inserts a customer into the collection
	 * 
	 * @param customer The customer to be insert
	 * @return true if the customer could be inserted. Current always true
	 */
	public boolean insertCustomer(Customer customer) {
		customers.add(customer);
		return true;
	}

	@Override
	public Iterator<Customer> iterator() {
		return customers.iterator();
	}

	/**
	 * Checks whether a member with a given member id exists.
	 * 
	 * @param memberId the id of the member
	 * @return true iff member exists
	 * 
	 */
	public Customer search(String customerId) {
		for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
			Customer customer = iterator.next();
			if (customer.getId().equals(customerId)) {
				return customer;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return customers.toString();
	}

}
