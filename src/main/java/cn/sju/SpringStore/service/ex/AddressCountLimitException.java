package cn.sju.SpringStore.service.ex;

public class AddressCountLimitException extends ServiceException{

	/**
	 * 
	 */
	public AddressCountLimitException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AddressCountLimitException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AddressCountLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public AddressCountLimitException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AddressCountLimitException(Throwable cause) {
		super(cause);
	}
	
}
