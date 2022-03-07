package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import entities.Appliance;
import facade.Result;

/**
 * This Iterator implementation supplies a "read-only" version of Appliance
 * objects.
 */
public class SafeApplianceIterator implements Iterator<Result> {
	private Iterator<Appliance> iterator;
	private Result result = new Result();

	/**
	 * The user of SafeApplianceIterator must supply an Iterator to Appliance
	 * 
	 * @param iterator Iterator<Appliance>
	 */
	public SafeApplianceIterator(Iterator<Appliance> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setApplianceField(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}
}
