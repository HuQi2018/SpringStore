package cn.sju.SpringStore.service.ex;

public class FileEmptyException extends ServiceException{

	/**
	 * 
	 */
	public FileEmptyException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FileEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public FileEmptyException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FileEmptyException(Throwable cause) {
		super(cause);
	}
	
}
