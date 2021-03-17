package cn.sju.SpringStore.service.ex;

public class FileTypeException extends ServiceException{

	/**
	 * 
	 */
	public FileTypeException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FileTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public FileTypeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FileTypeException(Throwable cause) {
		super(cause);
	}
	
}
