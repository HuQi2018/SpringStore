package cn.sju.SpringStore.service.ex;

public class IllegalOperationException extends ServiceException{

	/**
	 * 
	 */
	public IllegalOperationException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public IllegalOperationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public IllegalOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public IllegalOperationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public IllegalOperationException(Throwable cause) {
		super(cause);
	}
	
}
