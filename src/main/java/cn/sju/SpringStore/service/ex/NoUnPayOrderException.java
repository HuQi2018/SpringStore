package cn.sju.SpringStore.service.ex;

public class NoUnPayOrderException extends ServiceException{

	/**
	 * 
	 */
	public NoUnPayOrderException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NoUnPayOrderException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NoUnPayOrderException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public NoUnPayOrderException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NoUnPayOrderException(Throwable cause) {
		super(cause);
	}
	
}
