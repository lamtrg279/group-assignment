package collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import entities.BackOrder;

/**
 * The collections class for BackOrder objects
 */
public class BackOrderList implements Iterable<BackOrder>, Serializable {
	private static final long serialVersionUID = 1L;
	private List<BackOrder> backOrders = new LinkedList<BackOrder>();
	private static BackOrderList backorderList;

	private BackOrderList() {
	}

	public static BackOrderList getInstance() {
		if (backorderList == null) {
			backorderList = new BackOrderList();
		}
		return backorderList;
	}

	/**
	 * Inserts a backorder into the collection
	 * 
	 * @param backorder The backorder to be inserted
	 */
	public void insertBackorder(BackOrder backorder) {
		backOrders.add(backorder);
	}

	@Override
	public Iterator<BackOrder> iterator() {
		return backOrders.iterator();
	}

	/**
	 * Checks whether a backorder with a given id exists.
	 * 
	 * @param backorderId the id of the backorder
	 * @return true if backorder exists
	 * 
	 */
	public BackOrder search(String backorderId) {
		for (Iterator<BackOrder> iterator = backOrders.iterator(); iterator.hasNext();) {
			BackOrder backorder = iterator.next();
			if (backorder.getId().equals(backorderId)) {
				return backorder;
			}
		}
		return null;
	}

	/**
	 * Removes a backorder in the collection
	 * 
	 * @param backorder The backorder to be removed
	 */
	public void removeBackOrder(BackOrder backOrder) {
		backOrders.remove(backOrder);
	}

	@Override
	public String toString() {
		return backOrders.toString();
	}

}
