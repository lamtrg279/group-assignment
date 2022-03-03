package facade;

public class Result extends DataTransfer {
	public static final int OPERATION_COMPLETED = 1;
	public static final int OPERATION_FAILED = 2;
	public static final int APPLIANCE_NOT_FOUND = 3;
	public static final int CUSTOMER_NOT_FOUND = 4;
	public static final int APPLIANCE_NO_REPAIR_PLAN = 6;

	private int resultCode;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
}
