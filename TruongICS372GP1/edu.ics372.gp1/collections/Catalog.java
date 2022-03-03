package collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import entities.Appliance;

/**
 * The collection class for Appliances objects
 */
public class Catalog implements Iterable<Appliance>, Serializable {
	private static final long serialVersionUID = 1L;
	private static Catalog catalog;
	private List<Appliance> appliances = new LinkedList<Appliance>();

	private Catalog() {
	}

	public static Catalog getInstance() {
		if (catalog == null) {
			catalog = new Catalog();
		}
		return catalog;
	}

	/**
	 * Inserts an appliance into the collection
	 * 
	 * @param appliance The appliance to be inserted
	 * @return true if the appliance could be inserted. Currently always true
	 */
	public boolean insertAppliance(Appliance appliance) {
		appliances.add(appliance);
		return true;
	}

	@Override
	public Iterator<Appliance> iterator() {
		return appliances.iterator();
	}

}
