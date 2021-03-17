package cn.sju.SpringStore.service.ex;

public class ProductOutOfStockException extends ServiceException {

	/**
	 * 
	 */
	public ProductOutOfStockException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ProductOutOfStockException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ProductOutOfStockException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ProductOutOfStockException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ProductOutOfStockException(Throwable cause) {
		super(cause);
	}
	
}
