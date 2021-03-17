package cn.sju.SpringStore.service.ex;

public class CreateOrderException extends ServiceException{

	/**
	 * 
	 */
	public CreateOrderException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CreateOrderException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CreateOrderException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CreateOrderException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CreateOrderException(Throwable cause) {
		super(cause);
	}
	
}
