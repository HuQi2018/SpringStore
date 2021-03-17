package cn.sju.SpringStore.service.ex;

public class FileSizeException extends ServiceException{

	/**
	 * 
	 */
	public FileSizeException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FileSizeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public FileSizeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FileSizeException(Throwable cause) {
		super(cause);
	}
	
}
