package allutils.other;

import org.apache.commons.lang.StringUtils;

public class DataType {
	/**
	 * 该方法完成的功能是转化字符串类型的数组为整形数组
	 * 
	 * @param sids
	 * @return
	 */
	public static Integer[] converterStringArray2IntegerArray(String[] sids) {
		if (sids != null && sids.length > 0) {
			Integer[] ids = new Integer[sids.length];
			for (int i = 0; i < sids.length; i++) {
				if (StringUtils.isNotBlank(sids[i])) {
					ids[i] = Integer.parseInt(sids[i]);
				}
			}
			return ids;
		}
		return null;
	}

	/**
	 * 利用给定的流水位生成第一个流水号
	 *     * 流水位 3 ---第一个流水号001 
	 *     * 流水位 4 ---第一个流水号004
	 * @param glideBit
	 * @return
	 */
	public static String geneFirstGlideNumber(Integer glideBit) {
		if (glideBit == null || glideBit < 3) {
			glideBit = 3;
		}

		String glideNumber = "";
		for (int i = 0; i < glideBit - 1; i++) {
			glideNumber = glideNumber + "0";
		}
		glideNumber = glideNumber + "1";
		return glideNumber;
	}

	/**
	 * 根据当前的流水号，生成下一个流水号
	 *   * 当前是 001  下一个 002
	 *   * 当前是 0005  下一个 0006
	 * @param curGlideNumber 当前的流水号
	 * @return
	 *   String 10005    ----整形  10005
	 *   整形  10005+1    ----整形  10006
	 *   整形  10006     ----String 10006
	 *   String 10006   ----截取  0006
	 * 
	 */
	public static String geneNextGlideNumber(String curGlideNumber) {
		if(StringUtils.isBlank(curGlideNumber)){
			throw new RuntimeException("不能计算下一个流水号");
		}
		curGlideNumber="1"+curGlideNumber;
		Integer icurGlideNumber=Integer.parseInt(curGlideNumber);
		icurGlideNumber++;
		curGlideNumber=icurGlideNumber+"";
		
		curGlideNumber=curGlideNumber.substring(1);
		
		return curGlideNumber;
	}

	public static void main(String[] args) {
		System.err.println(geneNextGlideNumber("00005"));
	}
}
