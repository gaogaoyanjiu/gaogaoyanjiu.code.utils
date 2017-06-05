package allutils.other;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoader;

import com.opensymphony.xwork2.ActionContext;

import icoal.acl.impl.users.model.SysUser;
import icoal.core.exceptions.BusinessException;

/**
 * 
 * 缓存处理 工具类
 * @author zhangliang
 * @version V1.0
 * 
 */
public class ContextCach {
	public static ServletContext context = null;
	private static ContextCach instance;
	private static SysUser loginUser = null;
	private static String roles;
	private static HttpServletRequest request;

	private ContextCach() {
		
		if (context == null) {
			context = ContextLoader.getCurrentWebApplicationContext()
					.getServletContext();
		}
	}

	// 单例模式
	public static synchronized ContextCach getInstance() {
		if (null == instance)
			instance = new ContextCach();
		return instance;
	}

	public void setAlarmProcessList(String name,
			List<AlarmProcessBo> alarmProcessList) {
		getInstance();
		context.setAttribute(name, alarmProcessList);
	}

	public void setChcoList(String name, List<AlarmProcessBo> chcoList) {
		getInstance();
		context.setAttribute(name, chcoList);
	}

	public void setAllCacheList(String name, List<AlarmProcessVo> allList) {
		getInstance();
		context.setAttribute(name, allList);
	}
	
	/**
	 * 报警处理缓存集合
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AlarmProcessBo> getAlarmProcessList(String name) {
		getInstance();
		return (List<AlarmProcessBo>) context.getAttribute(name);
	}

	/**
	 * ch4和co报警集合
	 * 
	 * @param string
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AlarmProcessBo> getChcoList(String name) {
		return (List<AlarmProcessBo>) context.getAttribute(name);
	}

	/**
	 * 所有报警数据集合
	 * 
	 * @param string
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AlarmProcessVo> getAllCacheList(String name) {
		return (List<AlarmProcessVo>) context.getAttribute(name);
	}
	
	// 从session缓存中获取用户信息
	public static SysUser getSessionUser() {
		ActionContext ctx = ActionContext.getContext();
		request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		loginUser = (SysUser) request.getSession().getAttribute("user");
		// loginUser = (SysUser) context.get("LOGIN_USER", Scope.SESSION);
		if (loginUser == null) {
			throw new BusinessException("loginUser is null,session is timeout;");
		}
		return loginUser;
	}

	// 从session缓存中获取用户角色
	public static String getRoles() {
		ActionContext ctx = ActionContext.getContext();
		request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		// roles = context.get("ROLENAME", Scope.SESSION) == null ? "" :
		// context.get("ROLENAME", Scope.SESSION).toString();
		roles = request.getSession().getAttribute("rolename") == null ? ""
				: request.getSession().getAttribute("rolename").toString();
		return roles;
	}


}
