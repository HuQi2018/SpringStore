package cn.sju.SpringStore.util;

public class ResponseResult<T> {

    private Integer state;
    private String message;
    private T data;

    public ResponseResult() {
	}
    
    /**
	 * @param state
	 */
	public ResponseResult(Integer state) {
		super();
		this.state = state;
	}

	/**
	 * @param state
	 * @param message
	 */
	public ResponseResult(Integer state, String message) {
		super();
		this.state = state;
		this.message = message;
	}

	/**
	 * @param state
	 * @param message
	 * @param data
	 */
	public ResponseResult(Integer state, String message, T data) {
		super();
		this.state = state;
		this.message = message;
		this.data = data;
	}

	public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}