package cn.sju.SpringStore.service.ex;

public class FileIOException extends ServiceException{

	/**
	 * 
	 */
	public FileIOException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FileIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FileIOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public FileIOException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FileIOException(Throwable cause) {
		super(cause);
	}
	
}
