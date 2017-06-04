package allutils;

/**
 * 
 * @Description: 提示信息bean
 * @author lidong
 * @date 2013-10-16 下午3:02:39
 * 
 */
public class MessageBean {

	private boolean success;
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageBean() {
		// TODO Auto-generated constructor stub
	}

	public MessageBean(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
}
