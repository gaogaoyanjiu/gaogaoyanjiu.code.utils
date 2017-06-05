package allutils.constant;

import allutils.other.ContextCach;

/**
 * 
 * 常量 工具类
 * @author zhangliang
 * @version V1.0
 * 
 */
public class Constant {
	//database Link 常量     数据库的jdbc链接库需要创建远程的dblink 数据库链路  ,名称为   hdmy_security
	//public static final String DBLINK_hdmy_security = "@hdmy_security";
	public static final String DBLINK_hdmy_security = "@HDMY_SECURITY";
	//public static final String DBLINK_hdmy_security = "";
	
	public static final String OFFLINE_NORMAL = "正常";
	public static final String OFFLINE_NOBUILD = "离线(未建设)";
	public static final String OFFLINE_DATA = "联网故障";
	public static final String OFFLINE_SUBSYS = "子系统异常";
	public static final String OFFLINE_COLLECTOR = "采集器异常";
	public static final String SYSTYPE_RYDW = "人员定位数据上传异常";
	public static final String SYSTYPE_AQJC = "安全监测数据上传异常";
	public static final String SYSTYPE_WSCF = "瓦斯抽放数据上传异常";
	public static final String OFFLINE_RYDWYC = "人员定位子系统接口生成数据异常";
	public static final String OFFLINE_AQJCYC = "安全监测子系统接口生成数据异常";
	public static final String OFFLINE_WSCFYC = "瓦斯抽放子系统接口生成数据异常";
	public static final String OFFLINE_RYDW = "人员定位采集器网络中断";
	public static final String OFFLINE_AQJC = "安全监测采集器网络中断";

	// 隐患状态编码(1.未整改2.已整改3.未复查4.复查未通过5.复查通过6.已销号)
	public static final String HTCODE_NOT_RECTIFY = "1";
	public static final String HTNAME_NOT_RECTIFY = "未整改";

	public static final String HTCODE_ALREADY_RECTIFY = "2";
	public static final String HTNAME_ALREADY_RECTIFY = "已整改";

	public static final String HTCODE_NOT_CHECK = "3";
	public static final String HTNAME_NOT_CHECK = "已申请复查";

	public static final String HTCODE_CHECK_NOTPASS = "4";
	public static final String HTNAME_CHECK_NOTPASS = "复查未通过";

	public static final String HTCODE_CHECK_PASS = "5";
	public static final String HTNAME_CHECK_PASS = "复查通过";

	public static final String HTCODE_DESTROY = "6";
	public static final String HTNAME_DESTROY = "已销号";

	public static final String HTCODE_NOT_URGE = "0";
	public static final String HTNAME_NOT_URGE = "未督促";

	public static final String HTCODE_URGE = "1";
	public static final String HTNAME_URGE = "已督促";

	// 审批状态编码(1.未提交2.审批中3.审批未通过4.已发布)
	public static final String EXAMCODE_NOT_SUBMIT = "1";
	public static final String EXAMNAME_NOT_SUBMIT = "未提交";

	public static final String EXAMCODE_EXAMINING = "2";
	public static final String EXAMNAME_EXAMINING = "审批中";

	public static final String EXAMCODE_NOT_EXAMINED = "3";
	public static final String EXAMNAME_NOT_EXAMINED = "审批未通过";

	public static final String EXAMCODE_PUBLISH = "4";
	public static final String EXAMNAME_PUBLISH = "已发布";

	// 审批阶段编码（0.未审批1.一级审批2二级审批3三级审批）
	public static final String STEPCODE_NOT_EXAMINE = "0";
	public static final String STEPNAME_NOT_EXAMINE = "未审批";

	public static final String STEPCODE_EXAMINING_FIRST = "1";
	public static final String STEPNAME_EXAMINING_FIRST = "一级审批";

	public static final String STEPCODE_EXAMINING_SECOND = "2";
	public static final String STEPNAME_EXAMINING_SECOND = "二级审批";

	public static final String STEPCODE_EXAMINING_THIRD = "3";
	public static final String STEPNAME_EXAMINING_THIRD = "三级审批";

	public static final String STEPCODE_EXAMINING_FORTH = "4";
	public static final String STEPNAME_EXAMINING_FORTH = "审批完成";

	// 审批记录状态编码(1.一级审批通过2.一级审批未通过3.二级审批通过4.二级审批未通过5.三级审批通过6.三级审批未通过)
	public static final String RECTIFYCODE_PASS_FIRST = "1";
	public static final String RECTIFYNAME_PASS_FIRST = "一级审批通过";

	public static final String RECTIFYCODE_NOTPASS_FIRST = "2";
	public static final String RECTIFYNAME_NOTPASS_FIRST = "一级审批未通过";

	public static final String RECTIFYCODE_PASS_SECOND = "3";
	public static final String RECTIFYNAME_PASS_SECOND = "二级审批通过";

	public static final String RECTIFYCODE_NOTPASS_SECOND = "4";
	public static final String RECTIFYNAME_NOTPASS_SECOND = "二级审批未通过";

	public static final String RECTIFYCODE_PASS_THIRD = "5";
	public static final String RECTIFYNAME_PASS_THIRD = "三级审批通过";

	public static final String RECTIFYCODE_NOTPASS_THIRD = "6";
	public static final String RECTIFYNAME_NOTPASS_THIRD = "三级审批未通过";

	public static final String STAPRO_0="销售统计";
	public static final String STAPRO_1="月产销存统计";
	public static final String STAPRO_2="月剥离统计";
	public static final String STAPRO_3="月掘进统计";
	
	
	
	/**
	 * 
	 * 获取矿图审批状态名称
	 * 
	 * @param statusCode
	 * @return
	 */
	public static String getStaProName(String type) {
		if (type.equals("1")) {
			return STAPRO_0;
		}else if (type.equals("1")) {
			return STAPRO_1;
		} else if (type.equals("2")) {
			return STAPRO_2;
		} else if (type.equals("3")) {
			return STAPRO_3;
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * 获取矿图审批状态名称
	 * 
	 * @param statusCode
	 * @return
	 */
	public static String getDrawStatusName(String statusCode) {
		String statusName = "";
		if (statusCode.equals("1")) {
			statusName = "未提交";
		} else if (statusCode.equals("2")) {
			statusName = "审批中";
		} else if (statusCode.equals("3")) {
			statusName = "审批未通过";
		} else if (statusCode.equals("4")) {
			statusName = "已发布";
		}
		return statusName;

	}

	
	/**
	 * 
	 * 获取矿图审批阶段名称
	 * 
	 * @param stepCode
	 * @return
	 */
	public static String getDrawStepName(String stepCode) {
		String stepName = "未审批";
		if (stepCode.equals("1")) {
			stepName = "一级审批";
		} else if (stepCode.equals("2")) {
			stepName = "二级审批";
		} else if (stepCode.equals("3")) {
			stepName = "三级审批";
		} else {
			stepName = "审批完成";
		}
		return stepName;
	}

	/**
	 * 
	 * 获取矿图审批记录状态名称
	 * 
	 * @param stepCode
	 * @return
	 */
	public static String getRectifyStateName(String stateCode) {
		String stateName = "";
		if (stateCode.equals("1")) {
			stateName = "一级审批通过";
		} else if (stateCode.equals("2")) {
			stateName = "一级审批未通过";
		} else if (stateCode.equals("3")) {
			stateName = "二级审批通过";
		} else if (stateCode.equals("4")) {
			stateName = "二级审批未通过";
		} else if (stateCode.equals("5")) {
			stateName = "三级审批通过";
		} else if (stateCode.equals("6")) {
			stateName = "三级审批未通过";
		}
		return stateName;
	}

	/**
	 * 
	 * 根据登陆人角色获取审批阶段
	 * 
	 * @return
	 */
	public static String getStepCodeFromUser() {
		String step = "";
		String rolename = ContextCach.getRoles();
		if (rolename != null && !rolename.equals("")) {
			if (rolename.indexOf("煤矿机电部部长") != -1
					|| rolename.indexOf("煤矿机电部长") != -1
					|| rolename.indexOf("生产技术部部长") != -1
					|| rolename.indexOf("生产技术部长") != -1
					|| rolename.indexOf("生产技术部副部长") != -1
					|| rolename.indexOf("生产技术副部长") != -1) {
				step += "1,";
			}
			if (rolename.indexOf("机电矿长") != -1
					|| rolename.indexOf("总工程师") != -1) {
				step += "2,";
			}
			if (rolename.indexOf("矿长") != -1) {
				step += "3,";
			}
			if (rolename.indexOf("煤炭事业部") != -1) {
				step += "4,";
			}
			/*if (rolename.indexOf("科员") != -1 || rolename.indexOf("调度室") != -1) {
				step += "0,";
			}*/
			if(step!=null && !"".equals(step)){
				step = step.substring(0, step.length() - 1);
			}
			
		}
		return step;
	}

	public static void main(String[] args) {
		String step = "1,";
		step = step.substring(0, step.length() - 1);
		System.out.println(step);
	}
}
