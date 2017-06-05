package allutils.poiutils;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FileUtilExcl {
	
	/**
	 * 
	 * 单个文件上传
	 * @author zhangliang
	 * @version V1.0
	 * 
	 */
	public static void upFile(File uploadFile,String fileName,String filePath){
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		FileInputStream is = null;
		BufferedInputStream bis = null;
		File file = new File(filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		File f = new File(filePath+"/"+fileName);
		try {
			is = new FileInputStream(uploadFile);
			bis = new BufferedInputStream(is);
			fos = new FileOutputStream(f);
			bos = new BufferedOutputStream(fos);
			byte[] bt = new byte[4096];
			int len = 0;
			while((len = bis.read(bt))>0){
				bos.write(bt, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
				try {
					if(null != bos){
					bos.close();
					bos = null;
					}
					if(null != fos){
						fos.close();
						fos= null;
					}
					if(null != is){
						is.close();
						is=null;
					}
					
					if (null != bis) {
						bis.close();
						bis = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
	
	
	/**
	 * 文件下载
	 * @param response
	 * @param downloadFile
	 */
	public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String downloadFile, String fileName) {
		
		BufferedInputStream bis = null;
		InputStream is = null;
		OutputStream os = null;
		BufferedOutputStream bos = null;
		try {
			File file=new File(downloadFile); //文件的声明
	        is = new FileInputStream(file);  //文件流的声明
	        os = response.getOutputStream(); //重点突出(特别注意),通过response获取的输出流，作为服务端往客户端浏览器输出内容的一个通道
	        // 为了提高效率使用缓冲区流
	        bis = new BufferedInputStream(is);
	        // 处理下载文件名的乱码问题
	        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
	        	fileName = new String(fileName.getBytes("GB2312"),"ISO-8859-1");
	        } else {
	        	// 对文件名进行编码处理中文问题
	        	fileName = java.net.URLEncoder.encode(fileName, "UTF-8");// 处理中文文件名的问题
	        	fileName = new String(fileName.getBytes("UTF-8"), "GBK");// 处理中文文件名的问题
	        }
	        response.reset(); // 重点突出
	        response.setCharacterEncoding("UTF-8"); // 重点突出
	        response.setContentType("application/octet-stream");// 不同类型的文件对应不同的MIME类型 // 重点突出
	        // inline在浏览器中直接显示，不提示用户下载
	        // attachment弹出对话框，提示用户进行下载保存本地
	        // 默认为inline方式
	        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
	        //  response.setHeader("Content-Disposition", "attachment; filename="+fileName); // 重点突出
	        int bytesRead = 0;
	        byte[] buffer = new byte[1024];
	        while ((bytesRead = bis.read(buffer)) != -1){ //重点
	        	bos.write(buffer, 0, bytesRead);// 将文件发送到客户端
	        }
	        
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		} finally {
			// 特别重要
			// 1. 进行关闭是为了释放资源
			// 2. 进行关闭会自动执行flush方法清空缓冲区内容
			try {
				if (null != bis) {
					bis.close();
					bis = null;
				}
				if (null != bos) {
					bos.close();
					bos = null;
				}
				if (null != is) {
					is.close();
					is = null;
				}
				if (null != os) {
					os.close();
					os = null;
				}
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	        bos = new BufferedOutputStream(os);
	}
	
	/**
	 * 文件下载
	 * @param response
	 * @param downloadFile
	 */
	public static void downloadFile(HttpServletResponse response, String downloadFile, String showFileName) {
		
		BufferedInputStream bis = null;
		InputStream is = null;
		OutputStream os = null;
		BufferedOutputStream bos = null;
		try {
			File file=new File(downloadFile); //:文件的声明
	        String fileName=file.getName();
	        is = new FileInputStream(file);  //:文件流的声明
	        os = response.getOutputStream(); // 重点突出
	        bis = new BufferedInputStream(is);
	        bos = new BufferedOutputStream(os);
	        // 对文件名进行编码处理中文问题
	        fileName = java.net.URLEncoder.encode(showFileName, "UTF-8");// 处理中文文件名的问题
	        fileName = new String(fileName.getBytes("UTF-8"), "GBK");// 处理中文文件名的问题
	        response.reset(); // 重点突出
	        response.setCharacterEncoding("UTF-8"); // 重点突出
	        response.setContentType("application/octet-stream");// 不同类型的文件对应不同的MIME类型 // 重点突出
	        // inline在浏览器中直接显示，不提示用户下载
	        // attachment弹出对话框，提示用户进行下载保存本地
	        // 默认为inline方式
	        response.setHeader("Content-Disposition", "attachment; filename="+fileName); // 重点突出
	        int bytesRead = 0;
	        byte[] buffer = new byte[1024];
	        while ((bytesRead = bis.read(buffer)) != -1){ //重点
	            bos.write(buffer, 0, bytesRead);// 将文件发送到客户端
	        }
	        
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		} finally {
			// 特别重要
	        // 1. 进行关闭是为了释放资源
	        // 2. 进行关闭会自动执行flush方法清空缓冲区内容
			try {
				if (null != bis) {
					bis.close();
					bis = null;
				}
				if (null != bos) {
					bos.close();
					bos = null;
				}
				if (null != is) {
					is.close();
					is = null;
				}
				if (null != os) {
					os.close();
					os = null;
				}
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
	
	
	/**
	 * 文件导出
	 * @param response
	 * @param downloadFile
	 */
	public static void downloadExcel(HttpServletRequest request,HttpServletResponse response, HSSFWorkbook work,String excelName) {
		OutputStream out = null;
		
		try {
			//设置成这样可以不用保存在本地再输出 通过rseponse流输出，直接出书到客户端浏览器中
			out = response.getOutputStream();
			
			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				excelName = new String(excelName.getBytes("GB2312"),"ISO-8859-1");
	        } else {
	        	// 对文件名进行编码处理中文问题
	        	excelName = java.net.URLEncoder.encode(excelName, "UTF-8");// 处理中文文件名的问题
	        	excelName = new String(excelName.getBytes("UTF-8"), "GBK");// 处理中文文件名的问题
	        }
	        response.reset(); 
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/octet-stream");// 不同类型的文件对应不同的MIME类型 
	        // inline在浏览器中直接显示，不提示用户下载
	        // attachment弹出对话框，提示用户进行下载保存本地
	        // 默认为inline方式
	        response.setHeader("Content-Disposition", "attachment; filename="+excelName); 
	        work.write(out);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(null != out){
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	        
	        
	}
	
	
	
}


