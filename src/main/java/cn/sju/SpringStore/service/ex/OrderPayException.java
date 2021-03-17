package cn.sju.SpringStore.service.ex;

public class OrderPayException extends ServiceException{

	/**
	 * 
	 */
	public OrderPayException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public OrderPayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OrderPayException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public OrderPayException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public OrderPayException(Throwable cause) {
		super(cause);
	}
	
}
