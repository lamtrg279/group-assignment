package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import entities.BackOrder;
import facade.Result;

public class SafeBackOrderIterator implements Iterator<Result> {
	private Iterator<BackOrder> iterator;
	private Result result = new Result();

	public SafeBackOrderIterator(Iterator<BackOrder> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setBackOrderField(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}

}
