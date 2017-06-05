package allutils.other;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import icoal.acl.impl.users.model.SysUser;

public class SessionUtils {

	/**
	 * 处理验证码
	 * @param request
	 * @return
	 */
	public static boolean isCheckNum(HttpServletRequest request) {
		//获取已经存在的session
		HttpSession session=request.getSession(false);
		
		if(session==null){
			return false;
		}
		
		String check_number_key=(String)session.getAttribute("CHECK_NUMBER_KEY");
		if(StringUtils.isBlank(check_number_key)){
			return false;
		}
		
		//获取jsp页面文本框中输入的值
		//<input name="checkNum" type="text" value="" id="checkNum" style="width: 80">
		String saved=request.getParameter("checkNum");
		if(StringUtils.isBlank(saved)){
			return false;
		}
		
		//比对session中存放的值和页面文本框输入的验证码的值
		return check_number_key.equalsIgnoreCase(saved);
		
	}

	/**
	 * 保存当前登录用户的信息到session中
	 * @param request
	 * @param sysUser
	 */
	public static void setSysUserToSession(HttpServletRequest request,SysUser sysUser) {
        HttpSession session=request.getSession();
		if(sysUser==null){
			return;
		}
		session.setAttribute("sysUserKey", sysUser);
	}

	/**
	 * 从session中获取当前登陆用户的信息
	 * @param request
	 * @return
	 */
	public static SysUser getSysUserFormSession(HttpServletRequest request) {
		  HttpSession session=request.getSession(false);
		  if(session==null){
			  return null;
		  }
		  SysUser SysUser=(SysUser)session.getAttribute("sysUserKey");
		  return SysUser;
	}

}
