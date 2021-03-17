package cn.sju.SpringStore.service.ex;

public class CartNotFoundException extends ServiceException {

	/**
	 * 
	 */
	public CartNotFoundException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CartNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CartNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CartNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CartNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
