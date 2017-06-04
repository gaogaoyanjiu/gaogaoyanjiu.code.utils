package allutils;

/**
 * @Title: CommResult.java
 * @Package cn.tdtk.common.util
 * @Description: 通用的结果集，主要用于方法返回多个值
 * @author fuchao
 * @date 2013-3-25 上午09:13:44
 * @version V1.0
 */
public class CommResult<B,R, M> {
	private B isSuccess;
	private R result;
	private M msg;

	/**
	 * 返回是否成功
	 * @return isSuccess
	 */
	public B getIsSuccess() {
		return isSuccess;
	}

	/**
	 * 
	 * @param isSuccess
	 *            要设置的 isSuccess
	 */
	public void setIsSuccess(B isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * 成功之后返回的结果
	 * @return result
	 */
	public R getResult() {
		return result;
	}

	/**
	 * @param result
	 *            要设置的 result
	 */
	public void setResult(R result) {
		this.result = result;
	}

	/**
	 * 不成功返回的异常信息
	 * @return msg
	 */
	public M getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            要设置的 msg
	 */
	public void setMsg(M msg) {
		this.msg = msg;
	}

}
