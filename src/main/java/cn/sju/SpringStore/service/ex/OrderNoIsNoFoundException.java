package cn.sju.SpringStore.service.ex;

public class OrderNoIsNoFoundException extends ServiceException	{

	/**
	 * 
	 */
	public OrderNoIsNoFoundException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public OrderNoIsNoFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OrderNoIsNoFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public OrderNoIsNoFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public OrderNoIsNoFoundException(Throwable cause) {
		super(cause);
	}
	
}
