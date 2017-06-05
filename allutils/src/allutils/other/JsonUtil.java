package allutils.other;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import icoal.acl.impl.hrm.model.SysCode;
import net.sf.json.JSONArray;


/**
 * 
 * Json工具类
 * @author zhangliang
 * @version V1.0
 * 
 */
public class JsonUtil {

	@SuppressWarnings("unused")
	public static void writeJson2Page(String json, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			out = response.getWriter();
			out.write(json);
			out.flush();
		} catch (IOException localIOException) {
			if (out == null)
				return;
			out.close();
		} finally {
			if (out != null)
				out.close();
		}
	}
	
	/**
	 * 
	 * @Description: 将list集合转为json格式数据并返回
	 * @author 张代越
	 * @date 2017-3-28 上午10:28
	 *
	 */
	public static void writeList2Json(List list) {
		// 使用jsonlib将pageBean转化成json
		// 转换对象：使用JSONObject 转换对象
		// 转换集合：使用JSONArray转换集合（数组、Collection、List、Set...）
		JSONArray jsonArray = JSONArray.fromObject(list);
		String json = jsonArray.toString();

		// 使用response将json返回到前台
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String CodeList2Json(List<SysCode> codeList) {

		if (codeList != null && codeList.size() != 0) {
			StringBuilder sb = new StringBuilder();
			SysCode code = null;
			sb.append("[");
			for (int i = 0; i < codeList.size(); i++) {
				code = codeList.get(i);
				sb.append("{\"codeNum\":\"");
				sb.append(code.getCodeNum());
				sb.append("\",\"codeName\":\"");
				sb.append(code.getCodeName());
				sb.append("\"},");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			return sb.toString();
		}
		return "[]";
	}


}
