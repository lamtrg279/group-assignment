package collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import entities.BackOrder;

public class BackOrderList implements Iterable<BackOrder>, Serializable {
	private static final long serialVersionUID = 1L;
	private List<BackOrder> backorders = new LinkedList<BackOrder>();
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
	 * @param backorder The backorder to be insert
	 */
	public void insertBackorder(BackOrder backorder) {
		backorders.add(backorder);
	}

	@Override
	public Iterator<BackOrder> iterator() {
		return backorders.iterator();
	}

	/**
	 * Checks whether a backorder with a given id exists.
	 * 
	 * @param backorderId the id of the backorder
	 * @return true if backorder exists
	 * 
	 */
	public BackOrder search(String backorderId) {
		for (Iterator<BackOrder> iterator = backorders.iterator(); iterator.hasNext();) {
			BackOrder backorder = iterator.next();
			if (backorder.getId().equals(backorderId)) {
				return backorder;
			}
		}
		return null;
	}
}
