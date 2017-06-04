package allutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * ping  IP 工具类
 * @author zhangliang
 * @version V1.0
 * 
 */
public class PingUtils {
	//public static final Logger logger = LoggerFactory.getLogger(OffLineJob.class);
	public static final Map<String, String>  map = new HashMap<String ,String>();
	
	//ip集合
	public static final Map<String, String>	getMaps(){
		/*
		 * 不连沟煤矿  B2S  10.144.98.176
		 * 隆德煤矿    LDMK  10.160.68.158
		 * 小纪汗煤矿  YHK  10.160.81.94
		 * 肖家湾煤矿  JXR  10.156.32.41（安全）  10.156.32.42（人员）密码sacsis
		 * 石泉煤矿    297edff850021dc80150034c9ad9000c     10.141.135.158
		 * 
		 * 其他密码都是1
		 */
//		String CSIP="172.64.9.70";
//		map.put("297edff850021dc80150034c9ad9000c", CSIP);
//		map.put("LDMK", CSIP);
//		map.put("YHK", CSIP);
//		map.put("JXR", CSIP+","+"47.93.191.207");
//		map.put("B2S", CSIP);
		
		//map.put("LDMK", "192.168.253.3");
		
		
		map.put("297edff850021dc80150034c9ad9000c", "10.141.135.185");
		map.put("LDMK", "10.160.68.158");
		map.put("YHK", "10.160.81.94");
		map.put("JXR", "10.156.32.41,10.156.32.42");
		map.put("B2S", "10.144.98.176");
		
		return map;
		
	}
	
	public static boolean ping01(String ipAddress) throws Exception {
		if (ipAddress==null||"".equals(ipAddress)) {
			return false;
		}
		int timeOut =5000;// 超时应该在3钞以上
		boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut); // 当返回值是true时，说明host是可用的，false则不可。
		return status;
	}

	
	
	/**
	 * 判断是否能ping通指定的IP
	 * 
	 * @param ip
	 * @return
	 */
	public static final Boolean isPing(String ip) {
		
		/*if (ip==null||"".equals(ip)) {
			return false;
		}
		*/
		
		Boolean returnFlag = true;
		String pingCmd = null;
		String line = null;
		String returnStr = null;
		try {
			String osName = System.getProperties().getProperty("os.name");
			if (osName.startsWith("Windows")) {
				pingCmd = "cmd /c ping -n 4 " + ip;
			} else if (osName.startsWith("Linux")) {
				pingCmd = "ping -c 4 " + ip;
			} else {
				logger.info("Not support system!");
				return false;
			}

			Process pro = Runtime.getRuntime().exec(pingCmd);
			BufferedReader buf = new BufferedReader(new InputStreamReader(
					pro.getInputStream()));

			while ((line = buf.readLine()) != null) {
				System.out.println(line);
				returnStr += line;
			/*	if (returnStr.indexOf("100% loss") != -1
						|| returnStr.indexOf("timed out") != -1) {
					logger.info("与 " + ip + " 连接不畅通.");*/
				
				 if ((returnStr.indexOf("100%") != -1) || (returnStr.indexOf("100% loss") != -1) || (returnStr.indexOf("timed out") != -1) || 
				          (returnStr.indexOf("请求超时") != -1) || (returnStr.indexOf("100%  丢失") != -1)) {
				      logger.info("与 " + ip + " 连接不畅通.");
					returnFlag = false;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnFlag;
	}

}
