package cn.sju.SpringStore.service.ex;

public class OldPwdNotMatchException extends ServiceException{

	/**
	 * 
	 */
	public OldPwdNotMatchException() {
		super();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public OldPwdNotMatchException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public OldPwdNotMatchException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 */
	public OldPwdNotMatchException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public OldPwdNotMatchException(Throwable arg0) {
		super(arg0);
	}
	
}
