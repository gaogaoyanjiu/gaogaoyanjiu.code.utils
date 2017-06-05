/**  
* @Title: UploadFileProperty.java
* @Package cn.tdtk.common.util
* @Description: TODO(描述该文件做什么)
* @author leixinming
* @date 2013-3-24 下午04:19:11
* @version V1.0  
*/ 
package allutils.fileUploadUtils;

/**
 * 
 * 上传文件属性 工具类
 * @author zhangliang
 * @version V1.0
 * 
 */
public class UploadFileProperty {
	
	private String strFileName;//文件名
	private String strFileType;//文件类型
	private String strFilePath;//文件路径
	private int fileSize;//文件大小
	/**
	 * @return strFileName
	 */
	public String getStrFileName() {
		return strFileName;
	}
	/**
	 * @param strFileName 要设置的 strFileName
	 */
	public void setStrFileName(String strFileName) {
		this.strFileName = strFileName;
	}
	/**
	 * @return strFileType
	 */
	public String getStrFileType() {
		return strFileType;
	}
	/**
	 * @param strFileType 要设置的 strFileType
	 */
	public void setStrFileType(String strFileType) {
		this.strFileType = strFileType;
	}
	/**
	 * @return strFilePath
	 */
	public String getStrFilePath() {
		return strFilePath;
	}
	/**
	 * @param strFilePath 要设置的 strFilePath
	 */
	public void setStrFilePath(String strFilePath) {
		this.strFilePath = strFilePath;
	}
	/**
	 * @return fileSize
	 */
	public int getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize 要设置的 fileSize
	 */
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	
}
