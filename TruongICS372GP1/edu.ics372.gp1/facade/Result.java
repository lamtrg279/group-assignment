package facade;

public class Result extends DataTransfer {
	public static final int OPERATION_COMPLETED = 1;
	public static final int OPERATION_FAILED = 2;

	private int resultCode;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
}
