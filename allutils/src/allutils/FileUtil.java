package allutils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 * @Description: 文件操作工具类
 * @author lidong
 * @date 2013-7-10 上午11:10:10
 * 
 */
public class FileUtil {

	private final static Logger logger = Logger.getLogger(FileUtil.class);

	/**
	 * 
	 * 判断文件是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isExistFile(String filePath) {
		return new File(filePath).exists();
	}

	public static boolean CreateFile(File file) {
		if (file.exists()) {
			return false;
		}
		if (!file.getParentFile().exists()) {
			logger.info("目标文件所在路径不存在，准备创建。。。");
			if (!file.getParentFile().mkdirs()) {
				logger.info("创建目录文件所在的目录失败！");
				return false;
			}
		}

		try {
			if (file.createNewFile()) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * 创建目录
	 * 
	 * @param dir
	 */
	public static boolean createDir(File dir) {

		try {
			if (!dir.exists()) {
				if (!dir.getParentFile().exists()) {
					createDir(dir.getParentFile());
				} else {
					dir.mkdir();
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	/**
	 * 
	 * 文件重命名
	 * 
	 * @param file
	 * @param newPath
	 * @return
	 * @throws Exception
	 */
	public static boolean fileReName(File file, String newPath)
			throws Exception {
		File f_new = new File(newPath);
		return file.renameTo(f_new);

	}

	/**
	 * 
	 * 以流的方式获取资源
	 * 
	 * @param filePath
	 * @return
	 */
	public static InputStream getResourceAsStream(String filePath) {
		return Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(filePath);
	}

	/**
	 * 
	 * 文件移动，参数为文件
	 * 
	 * @param srcFile
	 * @param destPath
	 * @return
	 */
	public static boolean Move(File srcFile, String destPath) {
		File dir = new File(destPath);
		boolean success = srcFile.renameTo(new File(dir, srcFile.getName()));
		logger.info(srcFile.getName() + "移动到：" + destPath + "[" + success + "]");
		return success;
	}

	/**
	 * 
	 * 文件移动，参数为文件路径
	 * 
	 * @param srcFile
	 * @param destPath
	 * @return
	 */
	public static boolean Move(String srcFile, String destPath) {
		File file = new File(srcFile);
		File dir = new File(destPath);
		boolean success = file.renameTo(new File(dir, file.getName()));
		logger.info(srcFile + "移动到：" + destPath + "[" + success + "]");
		return success;
	}

	/**
	 * 
	 * 文件复制，参数为文件路径
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static void Copy(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldPath);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				logger.info(oldPath + "复制了：" + bytesum + "字节到：" + newPath);
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			logger.error("文件复制方法Exception:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 文件复制，参数为文件
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static void Copy(File oldfile, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldfile);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				logger.info(oldfile.getName() + "复制了：" + bytesum + "字节到："
						+ newPath);
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			logger.error("文件复制方法Exception:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 删除某个文件夹下的所有文件夹和文件
	 * 
	 * @param delpath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean deletefile(String delpath)
			throws FileNotFoundException, IOException {
		try {

			File file = new File(delpath);
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				System.out.println("2");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + "\\" + filelist[i]);
					if (!delfile.isDirectory()) {
						System.out.println("path=" + delfile.getPath());
						boolean a = delfile.delete();
						logger.info("删除文件:" + delfile.getName() + " [" + a
								+ "]");
					} else if (delfile.isDirectory()) {
						deletefile(delpath + "\\" + filelist[i]);
					}
				}
				boolean b = file.delete();
				logger.info("删除文件夹:" + file.getName() + " [" + b + "]");
			}

		} catch (FileNotFoundException e) {
			logger.error("删除文件方法 Exception:" + e.getMessage());
		}
		return true;
	}

	/**
	 * 
	 * 删除文件
	 * 
	 * @param file
	 */
	public static void deletefile(File file) {
		try {
			if (!file.isDirectory()) {
				file.delete();
				logger.info("删除成功！");
			} else if (file.isDirectory()) {
				File[] filelist = file.listFiles();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = filelist[i];
					if (!delfile.isDirectory()) {
						System.out.println("path=" + delfile.getPath());
						boolean a = delfile.delete();
						logger.info("删除文件:" + delfile.getName() + " [" + a
								+ "]");
					} else if (delfile.isDirectory()) {
						deletefile(filelist[i]);
					}
				}
				boolean b = file.delete();
				logger.info("删除文件夹:" + file.getName() + " [" + b + "]");
			}
		} catch (Exception e) {
			logger.error("删除文件方法 Exception:" + e.getMessage());
		}
	}

	/**
	 * 
	 * 获取文件最后更新时间
	 * 
	 * @param file
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static String getLastModifiedTime(File file, String pattern)
			throws Exception {
		DateFormat df = new SimpleDateFormat(pattern);
		Long time = file.lastModified();
		Calendar cd = Calendar.getInstance();
		cd.setTimeInMillis(time);
		return df.format(cd.getTime());

	}

	/**
	 * 
	 * 读取图片,返回byte[]
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(String path) throws Exception {
		File imageFile = new File(path);
		if (!imageFile.exists()) {
			imageFile.mkdir();
		}
		InputStream inStream = new FileInputStream(imageFile);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}

	/**
	 * 
	 * 读取图片,返回byte[]
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}

	/**
	 * 
	 * 读取文件，返回byte数组
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(File file) throws IOException {
		InputStream inStream = null;
		ByteArrayOutputStream outStream = null;
		try {
			inStream = new FileInputStream(file);
			outStream = new ByteArrayOutputStream();
			// 创建一个Buffer字符串
			byte[] buffer = new byte[1024];
			// 每次读取的字符串长度，如果为-1，代表全部读取完毕
			int len = 0;
			// 使用一个输入流从buffer里把数据读取出来
			while ((len = inStream.read(buffer)) != -1) {
				// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
				outStream.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inStream.close();
		}
		return outStream.toByteArray();
	}

	/**
	 * 
	 * 读取图片,返回OutputStream
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static OutputStream readOutputStream(String path) throws Exception {
		File imageFile = new File(path);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		if (!imageFile.exists()) {
			imageFile.mkdir();
		}
		InputStream inStream = new FileInputStream(imageFile);
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream;
	}

	/**
	 * 
	 * 下载附件
	 * 
	 * @param path
	 * @param fileName
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static int download(String path, String fileName,
			HttpServletResponse response) throws Exception {
		File file = new File(path);
		if (file.exists()) {
			return writeStream(new FileInputStream(file),
					prepareOutStream(response, fileName));
		} else {
		response.setContentType("text/html; charset=utf-8");
        PrintWriter pw=  response.getWriter();
        pw.write("<script type='text/javascript'>alert(' 文件不存在 !'); history.go(-1); </script>");
        pw.flush();
        pw.close();
			return 0;
		}

	}

	/**
	 * 
	 * 得到输出流
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static OutputStream prepareOutStream(HttpServletResponse response,
			String fileName) throws IOException {

		response.reset();// 重置输出流
		response.setContentType(checkMimeType(fileName));// 设置文件类型
		fileName = URLEncoder.encode(fileName, "UTF-8");// 解决乱码问题
		response.addHeader("Content-Disposition", "attachment;filename="
				+ fileName);// 设置文件名
		return response.getOutputStream();
	}

	/**
	 * 
	 * 输入流转输出流
	 * 
	 * @param in
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public static int writeStream(InputStream in, OutputStream out)
			throws IOException {
		try {
			int byteCount = 0;
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
			}
			out.flush();
			return byteCount;
		} finally {
			in.close();
			out.close();
		}
	}

	/**
	 * 
	 * 根据文件的扩展名来获取对应的输出流的HTTP MIME类型
	 * 
	 * @param filename
	 * @return
	 */
	public static String checkMimeType(String filename) {
		String ContentType;
		String type = filename.substring(filename.lastIndexOf(".")).trim()
				.toLowerCase();
		if (type.equals(".doc")) {
			ContentType = "application/msword";
		} else if (type.equals(".xls") || type.equals(".xlsx")) {
			ContentType = "application/vnd.ms-excel";
		} else if (type.equals("txt")) {
			ContentType = "text/plain";
		} else if (type.equals(".gif")) {
			ContentType = "image/gif";
		} else if (type.equals(".png")) {
			ContentType = "image/png";
		} else if (type.equals(".bmp")) {
			ContentType = "image/bmp";
		} else if (type.equals(".jpg") || type.equals(".jpeg")) {
			ContentType = "image/jpeg";
		} else if (type.equals(".html") || type.equals(".htm")) {
			ContentType = "text/html";
		} else if (type.equals(".pdf")) {
			ContentType = "application/pdf";
		} else if (type.equals(".xml")) {
			ContentType = "text/xml";
		} else if (type.equals(".rtf")) {
			ContentType = "application/rtf";
		} else if (type.equals(".ppt")) {
			ContentType = "application/vnd.ms-powerpoint";
		} else if (type.equals(".asf")) {
			ContentType = "video/x-ms-asf";
		} else if (type.equals(".avi")) {
			ContentType = "video/avi";
		} else if (type.equals(".zip")) {
			ContentType = "application/zip";
		} else if (type.equals(".wav")) {
			ContentType = "audio/wav";
		} else if (type.equals(".mp3")) {
			ContentType = "audio/mpeg3";
		} else if (type.equals(".mpg") || type.equals(".mpeg")) {
			ContentType = "video/mpeg";
		} else {
			ContentType = "application/octet-stream";
		}
		return ContentType;
	}

	public static void main(String[] args) throws Exception {

		// 获取文件最后更新时间
		File file = new File("D:\\tdtkdata\\RYDev.txt");
		String pattern = "yyyy-MM-dd HH:mm:ss";
		System.out.println(getLastModifiedTime(file, pattern));
		System.out.println(File.separator);
	}

}
