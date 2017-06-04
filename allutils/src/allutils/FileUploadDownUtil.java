package allutils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import icoal.acl.impl.common.util.PropertiesFileUtil;

/**
 * @Title: FileUploadDownUtil.java
 * @Package cn.tdtk.common.util
 * @Description: TODO文件上传下载工具
 * @author leixinming
 * @date 2013-3-23 上午10:39:28
 * @version V1.0
 */
public final class FileUploadDownUtil {

	private static File uploadFile = null;
	private static File tmpFile = null;
	private static String UPLOAD_PATH = "";//配置文件读取
	// 文件最大容量
	public static final long MAX_SIZE = -1;
	public static final long maxFileSize = 1024 * 1024 * 200L;// 200M
	// 上传文件类型
	public static String[] FILE_TYPE = null;//配置文件读取
	
	static {
		UPLOAD_PATH = PropertiesFileUtil.getPropertiesValueByKey("upLoadFile",
				"UPLOADPATH");
		FILE_TYPE = PropertiesFileUtil.getPropertiesValueByKey("upLoadFile",
		"UPLOADFILETYPE").split(",");
		uploadFile = new File(UPLOAD_PATH);
		if (!uploadFile.exists())
			uploadFile.mkdirs();
	}

	/**
	 * @Title: checkReloadFileType
	 * @Description: TODO验证文件是否为合法的上传文件类型， 是:true; 否: false
	 * @param @param name
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	private static boolean checkReloadFileType(String name) {
		boolean flag = false;
		String strext = FilenameUtils.getExtension(name).toLowerCase();
		for (String str : FILE_TYPE) {
			if (str.contains(strext)) {
				flag = true;
			}
			if (flag)
				break;
		}
		return flag;
	}

	/**
	 * @Title: checkFileType
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param items
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	private static boolean checkFileType(List<FileItem> items) {
		Iterator<FileItem> iterator = items.iterator();
		while (iterator.hasNext()) {
			FileItem fileItem = iterator.next();
			if (!fileItem.isFormField()) {// 是文件类型
				String name = fileItem.getName();
				if (!checkReloadFileType(name)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @Title: checkFileType
	 * @Description: TODO
	 * @param @param strFileName
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	private static boolean checkFileTypeByFileName(String strFileName) {
		boolean flag = false;
		String strext = FilenameUtils.getExtension(strFileName).toLowerCase();
		for (String str : FILE_TYPE) {
			if (str.contains(strext)) {
				flag = true;
			}
			if (flag)
				break;
		}
		return flag;
	}


	/**
	* @Title: saveFile
	* @Description: TODO保存文件到磁盘
	* @param @param file 文件对象
	* @param @param strFileName 要保存的文件名
	* @param @param strSaveDir 存储目录名
	* @param @return    设定文件
	* @return CommResult<Boolean,UploadFileProperty,String>    返回类型
	* @throws
	*/
	public static CommResult<Boolean,UploadFileProperty,String> saveFile(File file, String strFileName,
			String strSaveDir){
		CommResult<Boolean,UploadFileProperty,String> result = new CommResult<Boolean, UploadFileProperty, String>();
		try {
			UploadFileProperty fileProperty = new UploadFileProperty();
			if (!checkFileTypeByFileName(strFileName)) {
				result.setIsSuccess(false);
				result.setMsg("{\"success\":false,\"message\":\"上传的文件类型不正确\"}");
			}else{
				FileInputStream is = new FileInputStream(file);
				if (file.length() < maxFileSize) {
					
					String strUploadrealFileName = DateUtil.dateToString(new Date(),
							"yyyyMMddHHmmss")
							+ strFileName;
					fileProperty.setStrFileName(strFileName);
					fileProperty.setFileSize(Integer.parseInt(Long.toString(file
							.length())));
					fileProperty.setStrFilePath(UPLOAD_PATH + "\\"+strSaveDir+ "\\"
							+ strUploadrealFileName);
					fileProperty.setStrFileType(FilenameUtils.getExtension(strFileName)
							.toLowerCase());
					//存储目录的创建
					File destDirectory = new File(UPLOAD_PATH + "\\"
							+ strSaveDir);
					if (!destDirectory.exists()) {
						destDirectory.mkdir();
					}
					//文件的存储
					File realFile = new File(destDirectory, strUploadrealFileName);
					FileOutputStream fileOutputStream;
					fileOutputStream = new FileOutputStream(realFile);
					byte[] buffer = new byte[1024];
					int lenght = 0;
					while ((lenght = is.read(buffer)) != -1) {
						fileOutputStream.write(buffer, 0, lenght);
					}
					fileOutputStream.close();
					is.close();
					
					//创建索引，方便模糊搜索
					//LuneceUtil.createIndex(directory,indexPath1);
					
					//
					result.setIsSuccess(true);
					result.setResult(fileProperty);
					result.setMsg("{\"success\":true,\"message\":\"上传文件成功\"}");
				} else {
					result.setIsSuccess(false);
					result.setMsg("{\"success\":false,\"message\":\"上传文件不能超过200M\"}");
				}
			}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setIsSuccess(false);
			result.setMsg("{\"success\":false,\"message\":\"文件上传失败\"}");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setIsSuccess(false);
			result.setMsg("{\"success\":false,\"message\":\"文件上传失败\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setIsSuccess(false);
			result.setMsg("{\"success\":false,\"message\":\"文件上传失败\"}");
		}
		return result;
	}

	/**
	 * @Title: uploadFile
	 * @Description: TODO文件上传
	 * @param @param request
	 * @param @param response
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static Map uploadFile(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> returnMap = new HashMap<String, Object>();// 返回表单元素和上次文件的属性信息
		// 检查请求是否为multipart表单数据
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				// 创建一个DiskFileItemFactory对象，通过它来解析请求
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 设置数据超过maxThreshold时数据写入硬盘的tempDirectory 目录
				factory.setRepository(tmpFile);
				factory.setSizeThreshold(4096);// 设置缓冲区大小
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("utf-8");
				upload.setSizeMax(MAX_SIZE);
				List<FileItem> fileItems = new ArrayList();
				try {
					fileItems = upload.parseRequest(request); // 开始上传文件
				} catch (FileUploadException e) {
					e.printStackTrace();
				}
				// 检查文件是否符合上传类型
				if (!checkFileType(fileItems)) {
					JsonUtil.writeJson2Page(
							"{\"success\":false,\"message\":\"上传的文件类型不正确\"}",
							response);
					return null;
				}
				Iterator<FileItem> iterator = fileItems.iterator();
				Map<String, Object> formMap = new HashMap<String, Object>();// 存储表单元素
				while (iterator.hasNext()) {
					FileItem item = iterator.next();
					if (!item.isFormField()) {
						// item.getName()获取文件路径及其名称,filenameUtils.getName()获取文件的名称
						String fileName = FilenameUtils.getName(item.getName());
						// 检查文件大小是否符合
						if (item.getSize() < maxFileSize) {
							String strUploadrealFileName = DateUtil
									.dateToString(new Date(), "yyyyMMddHHmmss")
									+ fileName;
							File saveFile = new File(UPLOAD_PATH,
									strUploadrealFileName);
							item.write(saveFile);
							item.delete();
							// 上传文件属性Map
							UploadFileProperty fileProperty = new UploadFileProperty();
							fileProperty.setStrFileName(fileName);
							fileProperty.setFileSize(Integer.parseInt(Long
									.toString(item.getSize())));
							fileProperty.setStrFilePath(UPLOAD_PATH + "\\"
									+ strUploadrealFileName);
							fileProperty.setStrFileType(FilenameUtils
									.getExtension(fileName).toLowerCase());
							returnMap.put("FileProperty", fileProperty);// 将上传文件的属性信息装入返回Map中
						} else {
							JsonUtil
									.writeJson2Page(
											"{\"success\":false,\"message\":\"上传文件不能超过200M\"}",
											response);
							return null;
						}

					} else {
						String fname = item.getFieldName();
						String name = item.getString("utf-8");
						formMap.put(fname, name);
					}
				}
				returnMap.put("formElement", formMap);
				JsonUtil.writeJson2Page(
						"{\"success\":true,\"message\":\"文件上传成功\"}", response);
				return returnMap;
			} catch (FileUploadException e) {
				e.printStackTrace();
				JsonUtil.writeJson2Page(
						"{\"success\":false,\"message\":\"文件上传失败\"}", response);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				JsonUtil.writeJson2Page(
						"{\"success\":false,\"message\":\"文件上传失败\"}", response);
				return null;
			}
		} else {
			JsonUtil.writeJson2Page(
					"{\"success\":false,\"message\":\"文件上传失败\"}", response);
			return null;
		}
	}

	/**
	 * @Title: downLoadFile
	 * @Description: TODO下载文件工具
	 * @param @param request
	 * @param @param response
	 * @param @param strDownFilePath 下载文件的服务器路径
	 * @param @param downFileName 现在文件显示的文件名
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static void downLoadFile(HttpServletRequest request,
			HttpServletResponse response, String strDownFilePath,
			String downFileName) throws ServletException, IOException {
		// 下载的文件名和路径
		String strFileName = downFileName;
		String strFilePath = strDownFilePath;
		if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
			strFileName = URLEncoder.encode(strFileName, "UTF-8");// IE浏览器
		} else {
			strFileName = new String(strFileName.getBytes("UTF-8"), "ISO8859-1");// 其它浏览器
		}
		File file = new File(strFilePath);
		if (file.exists()) {
			int fileLength = (int) file.length();
			if (fileLength != 0) {
				response.setContentType("application/octet-stream");
				response.setHeader("Content-disposition",
						"attachment;filename=" + strFileName);
				response
						.setContentType("application/x-download; charset=utf-8");
				String length = String.valueOf(fileLength);
				response.setHeader("Content_Length", length);
				FileInputStream in = new FileInputStream(file);
				int n = 0;
				OutputStream o = response.getOutputStream();
				byte b[] = new byte[1024];
				while ((n = in.read(b)) != -1) {
					o.write(b, 0, n);
				}
				o.flush();
				o.close();
			}
		} else {
			JsonUtil.writeJson2Page(
					"{\"success\":false,\"message\":\"下载文件不存在\"}", response);
		}
	}
	
	/**
	* @Title: deleteFile
	* @Description: TODO直接删除文件
	* @param @param strFilePath
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	*/
	public static boolean deleteFile(String strFilePath){
		boolean flag = false;  
	    File file = new File(strFilePath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}
	
}
