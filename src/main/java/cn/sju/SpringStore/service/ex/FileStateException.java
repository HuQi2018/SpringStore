package cn.sju.SpringStore.service.ex;

public class FileStateException extends ServiceException{

	/**
	 * 
	 */
	public FileStateException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FileStateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public FileStateException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FileStateException(Throwable cause) {
		super(cause);
	}
	
}
