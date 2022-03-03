package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import entities.RepairPlan;
import facade.Result;

public class SafeRepairPlanIterator implements Iterator<Result> {
	private Iterator<RepairPlan> iterator;
	private Result result = new Result();

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
