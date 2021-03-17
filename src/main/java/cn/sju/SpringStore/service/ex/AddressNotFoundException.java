package cn.sju.SpringStore.service.ex;

public class AddressNotFoundException extends ServiceException {

	/**
	 * 
	 */
	public AddressNotFoundException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AddressNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AddressNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public AddressNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AddressNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
