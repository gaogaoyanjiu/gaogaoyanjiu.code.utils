package allutils.constant;

/**
 * 
 * 提示信息bean工具类
 * @author zhangliang
 * @version V1.0
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
