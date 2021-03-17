package cn.sju.SpringStore.service.ex;

public class UserIsAlreadyDeleteException extends ServiceException{

	/**
	 * 
	 */
	public UserIsAlreadyDeleteException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserIsAlreadyDeleteException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param s
	 */
	public UserIsAlreadyDeleteException(String s) {
		super(s);
	}

	/**
	 * @param cause
	 */
	public UserIsAlreadyDeleteException(Throwable cause) {
		super(cause);
	}
	
}
