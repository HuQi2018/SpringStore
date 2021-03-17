package cn.sju.SpringStore.service.ex;

public class ProductNotFoundException extends ServiceException{

	/**
	 * 
	 */
	public ProductNotFoundException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ProductNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ProductNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ProductNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
