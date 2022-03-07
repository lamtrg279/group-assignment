package facade;

/**
 * This class is used for requesting many of the results of the company system's
 * business logic to user interface. It is a single class.
 */
public class Request extends DataTransfer {
	private static Request request;

	/**
	 * This is a singleton class. Hence the private constructor
	 */
	private Request() {
	}

	public static Request instance() {
		if (request == null) {
			request = new Request();
		}
		return request;
	}

}
