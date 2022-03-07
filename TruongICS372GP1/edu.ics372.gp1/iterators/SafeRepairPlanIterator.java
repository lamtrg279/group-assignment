package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import entities.RepairPlan;
import facade.Result;

/**
 * This Iterator implementation is tailor-made to supply a "read-only" version
 * of RepairPlan objects.
 */
public class SafeRepairPlanIterator implements Iterator<Result> {
	private Iterator<RepairPlan> iterator;
	private Result result = new Result();

	/**
	 * The user of SafeRepairPlanIterator must supply an Iterator to RepairPlan.
	 * 
	 * @param iterator Iterator<RepairPlan>
	 */
	public SafeRepairPlanIterator(Iterator<RepairPlan> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setRepairPlanField(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}

}
