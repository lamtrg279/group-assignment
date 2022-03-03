package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import entities.Customer;
import facade.Result;

/**
 * This Iterator implementation is tailor-made to supply a "read-only" version
 * of Customer objects. The user should supply an iterator to Customer as the
 * parameter to the constructor.
 */
public class SafeCustomerIterator implements Iterator<Result> {
	private Iterator<Customer> iterator;
	private Result result = new Result();

	/**
	 * The user of SafeIterator must supply an Iterator to Customer.
	 * 
	 * @param iterator Iterator<Customer>
	 */
	public SafeCustomerIterator(Iterator<Customer> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setCustomerField(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}
}
