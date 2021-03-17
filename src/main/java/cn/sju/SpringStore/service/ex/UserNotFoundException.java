package cn.sju.SpringStore.service.ex;

public class UserNotFoundException extends ServiceException {

    private static final long serialVersionUID = 6823682137712032655L;

	/**
	 * 
	 */
	public UserNotFoundException() {
		super();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public UserNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public UserNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 */
	public UserNotFoundException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public UserNotFoundException(Throwable arg0) {
		super(arg0);
	}
    

}