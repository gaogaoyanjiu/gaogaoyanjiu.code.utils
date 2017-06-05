package allutils.poiutils;



import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 
 * Excel工具类
 * @author zhangliang
 * @version V1.0
 * 
 */
public class ExportExcel {

	/**
	 * 导出煤矿基本信息
	* @Title: getExportExcelCoalInfo 
	* @param @param work
	* @param @param collbean    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void getExportExcelCoalInfo(HSSFWorkbook work, List<CollVo> collbean){
		
		 HSSFSheet sheet = work.createSheet("煤矿基本信息");
		 HSSFRow row = sheet.createRow(0);
		 row.createCell(0).setCellValue("二级单位名称");
		 row.createCell(1).setCellValue("煤矿名称");
		 row.createCell(2).setCellValue("所在地区");
		 row.createCell(3).setCellValue("煤矿开采类型");
		 row.createCell(4).setCellValue("煤矿性质");
		 row.createCell(5).setCellValue("联系人");
		 row.createCell(6).setCellValue("联系方式");
		 row.createCell(7).setCellValue("核对生产能力");
		 row.createCell(8).setCellValue("安全生产天数起始日期");
		 
		 row.createCell(9).setCellValue("通讯地址");
		 row.createCell(10).setCellValue("邮编");
		 row.createCell(11).setCellValue("控股比例(%)");
		 row.createCell(12).setCellValue("开拓方式");
		 row.createCell(13).setCellValue("开采方法");
		 row.createCell(14).setCellValue("建矿日期");
		 row.createCell(15).setCellValue("投产日期");
		 row.createCell(16).setCellValue("可采储量(万吨)");
		 
		 row.createCell(17).setCellValue("主要煤种");//
		 row.createCell(18).setCellValue("煤层平均厚度");//
		 row.createCell(19).setCellValue("煤层最大厚度");//
		 row.createCell(20).setCellValue("煤层平均斜角");//
		 row.createCell(21).setCellValue("煤层最大斜角");//
		 row.createCell(22).setCellValue("瓦斯等级");//
		 row.createCell(23).setCellValue("瓦斯相对涌出量");//
		 row.createCell(24).setCellValue("瓦斯绝对涌出量");//
		 
		 row.createCell(25).setCellValue("采煤工作面合计个数");
		 row.createCell(26).setCellValue("采煤工作面综采个数");
		 row.createCell(27).setCellValue("采煤工作面普采个数");
		 row.createCell(28).setCellValue("采煤工作面炮采个数");
		 row.createCell(29).setCellValue("采煤工作面其他个数");
		 row.createCell(30).setCellValue("采煤工作面合计产量");
		 row.createCell(31).setCellValue("采煤工作面综采产量");
		 row.createCell(32).setCellValue("采煤工作面普采产量");
		 row.createCell(33).setCellValue("采煤工作面炮采产量");
		 row.createCell(34).setCellValue("采煤工作面其他产量");
		  
		 row.createCell(35).setCellValue("掘进工作面合计个数");
		 row.createCell(36).setCellValue("掘进工作面综掘个数");
		 row.createCell(37).setCellValue("掘进工作面炮掘个数");
		 row.createCell(38).setCellValue("掘进工作面其他个数");
		 row.createCell(39).setCellValue("掘进工作面合计产量");
		 row.createCell(40).setCellValue("掘进工作面综掘产量");
		 row.createCell(41).setCellValue("掘进工作面炮掘产量");
		 row.createCell(42).setCellValue("掘进工作面其他产量");
		 row.createCell(43).setCellValue("工作面回采率");
		 row.createCell(44).setCellValue("采区回采率");
		 row.createCell(45).setCellValue("从事井下作业人数");
		 row.createCell(46).setCellValue("矿井全员效率");
		 
		 for(int i=0;i<collbean.size();i++){
			 CollVo bean = collbean.get(i);
			 row = sheet.createRow(i+1);
			 
			 row.createCell(0, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getUnitName());//二级公司
			 row.createCell(1, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollName());//煤矿名称
			 row.createCell(2, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollAire());//所在地区
			 row.createCell(3, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollKcway());//煤矿开采类型
			 row.createCell(4, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollNature());//煤矿性质
			 row.createCell(5, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollPeople());//联系人
			 row.createCell(6, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollPhone());//联系方式
			 row.createCell(7, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollCheakTatify());//核对生产能力
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
			 row.createCell(8, HSSFCell.CELL_TYPE_STRING ).setCellValue(df.format(bean.getCollSafedaysSdate()) ); //安全生产天数起始日期
			 row.createCell(9, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollAddress());//通讯地址
			 row.createCell(10, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollPostcode());//邮编
			 row.createCell(11, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollHold());//控股比例
			 row.createCell(12, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollPioneeWay());//开拓方式
			 row.createCell(13, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollPioneeMethod());//开采方法
//			 row.createCell(14, HSSFCell.CELL_TYPE_STRING ).setCellValue(df.format(bean.getCollConsctuionDate()));//建矿日期
//			 row.createCell(15, HSSFCell.CELL_TYPE_STRING ).setCellValue(df.format(bean.getCollPutintoDate()));//投产日期
			 row.createCell(14, HSSFCell.CELL_TYPE_STRING ).setCellValue(df.format(bean.getCollConsctuionDate()) ); //安全生产天数起始日期
			 row.createCell(15, HSSFCell.CELL_TYPE_STRING ).setCellValue(df.format(bean.getCollPutintoDate()) ); //安全生产天数起始日期
			 row.createCell(16, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollReserve());//可采储量
			 row.createCell(17, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollMainType());//主要煤种
			 row.createCell(18, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollAvePly());//煤层平均厚度
			 row.createCell(19, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollMaxPly());//煤层最大厚度
			 row.createCell(20, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollAveMitre());//煤层平均斜角
			 row.createCell(21, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollMaxMitre());//煤层最大斜角
			 row.createCell(22, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollGaseMine());//瓦斯等级
			 row.createCell(23, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollGaseRelative());//瓦斯相对涌出量
			 row.createCell(24, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollGaseAbsolute());//瓦斯绝对涌出量
			 
			 row.createCell(25, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkAllg());//采煤工作面合计个数
			 row.createCell(26, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkZcg());//采煤工作面综采个数
			 row.createCell(27, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkNcg());//采煤工作面普采个数
			 row.createCell(28, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkPcg());//采煤工作面炮采个数
			 row.createCell(29, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkElseg());//采煤工作面其他个数
			 row.createCell(30, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkAllc());//采煤工作面合计产量
			 row.createCell(31, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkZcc());//采煤工作面综采产量
			 row.createCell(32, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkNcc());//采煤工作面普采产量
			 row.createCell(33, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkPcc());//采煤工作面炮采产量
			 row.createCell(34, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkElsec());//采煤工作面其他产量
		
			 row.createCell(35, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkAllj());//掘进工作面合计个数
			 row.createCell(36, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkCjj());//掘进工作面综掘个数
			 row.createCell(37, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkPlj());//掘进工作面炮掘个数
			 row.createCell(38, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkElsej());//掘进工作面其他个数
			 row.createCell(39, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkAlljc());//掘进工作面合计产量
			 row.createCell(40, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkCjjc());//掘进工作面综掘产量
			 row.createCell(41, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkPljc());//掘进工作面炮掘产量
			 row.createCell(42, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkElsejc());//掘进工作面其他产量
			 row.createCell(43, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollCollface());//工作面回采率
			 row.createCell(44, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollWorkArea());//采区回采率
			 row.createCell(45, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollDownNumber());//从事井下作业人数
			 row.createCell(46, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollAllEfficiency());//矿井全员效率
		 }
		sheet = null;
		row = null;
	}

	/**
	 * 导出 人员组成信息
	* @Title: getExportExcelManning 
	* @param @param work
	* @param @param manningBean    设定文件 
	* @return void    返回类型 
	* @throws
	 */

	public static void getExportExcelManning(HSSFWorkbook work, List<ManningVo> manningBean) {
		
		 HSSFSheet sheet = work.createSheet("人员组成情况");
		 HSSFRow row = sheet.createRow(0);
		 row.createCell(0).setCellValue("二级单位名称");
		 row.createCell(1).setCellValue("煤矿名称");
//		 row.createCell(2).setCellValue("所在地区");
//		 row.createCell(3).setCellValue("煤矿开采类型");
//		 row.createCell(4).setCellValue("煤矿性质");
//		 row.createCell(5).setCellValue("联系人");
//		 row.createCell(6).setCellValue("联系方式");
//		 row.createCell(7).setCellValue("核对生产能力");
//		 row.createCell(8).setCellValue("安全生产天数起始日期");
		 for(int i=0;i<manningBean.size();i++){
			 ManningVo bean = manningBean.get(i);
			 row = sheet.createRow(i+1);
			 
			 row.createCell(0, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getUnitName());//二级公司
			 row.createCell(1, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollName());//煤矿名称
//			 row.createCell(2, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollAire());//所在地区
//			 row.createCell(3, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollKcway());//煤矿开采类型
//			 row.createCell(4, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollNature());//煤矿性质
//			 row.createCell(5, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollPeople());//联系人
//			 row.createCell(6, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollPhone());//联系方式
//			 row.createCell(7, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollCheakTatify());//核对生产能力
//			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
//			 row.createCell(8, HSSFCell.CELL_TYPE_STRING ).setCellValue(df.format(bean.getCollSafedaysSdate()) ); //安全生产天数起始日期
		 }
		sheet = null;
		row = null;
	}

	public static void getExportExcelQuficton(HSSFWorkbook work, List<QufictonVo> qufictonBean) {
		 HSSFSheet sheet = work.createSheet("煤矿资质信息");
		 HSSFRow row = sheet.createRow(0);
		 row.createCell(0).setCellValue("二级单位名称");
		 row.createCell(1).setCellValue("煤矿名称");
		 
		 for(int i=0;i<qufictonBean.size();i++){
			 QufictonVo bean = qufictonBean.get(i);
			 row = sheet.createRow(i+1);
			 row.createCell(0, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getUnitName());//二级公司
			 row.createCell(1, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollName());//煤矿名称
		 }
		sheet = null;
		row = null;
	
		
	}

	public static void getExportExcelDisaster(HSSFWorkbook work, List<DisasterVo> disasterBean) {
		 HSSFSheet sheet = work.createSheet("煤矿主要灾害");
		 HSSFRow row = sheet.createRow(0);
		 row.createCell(0).setCellValue("二级单位名称");
		 row.createCell(1).setCellValue("煤矿名称");
		 
		 for(int i=0;i<disasterBean.size();i++){
			 DisasterVo bean = disasterBean.get(i);
			 row = sheet.createRow(i+1);
			 row.createCell(0, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getUnitName());//二级公司
			 row.createCell(1, HSSFCell.CELL_TYPE_STRING ).setCellValue(bean.getCollName());//煤矿名称
		 }
		sheet = null;
		row = null;
	}
}
