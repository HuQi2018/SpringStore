package cn.sju.SpringStore.service.ex;

public class PasswordNotMatchException extends ServiceException {

    private static final long serialVersionUID = 4726518796909676941L;

	/**
	 * 
	 */
	public PasswordNotMatchException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PasswordNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public PasswordNotMatchException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PasswordNotMatchException(Throwable cause) {
		super(cause);
	}
    
    
}