package allutils;

/**
 * 
 * Excel导入工具类
 * @author zhangliang
 * @version V1.0
 * 
 */
public class ImportExcel {

		
		/** Excel文件的存放位置。注意是正斜线*/
		static String filePath="D:/test.xls";
		
		public static void main(String args[]){ 
			
			int i =9;
			String s = "9";
			String d = s.substring(0,s.indexOf("."));
			int sa = Integer.parseInt(d);
			System.out.println(sa);
			
			
			
	/*	try{
		// 创建对Excel工作簿文件的引用
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));


		// 创建对工作表的引用。
		// 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
		HSSFSheet sheet = workbook.getSheet("Sheet1");


		// 也可用getSheetAt(int index)按索引引用，
		// 在Excel文档中，第一张工作表的缺省索引是0，
		// 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);

		for(int i=1;i<=sheet.getLastRowNum();i++){
			HSSFRow row = sheet.getRow(i);
			for(int j=0;j<row.getLastCellNum();j++){
				HSSFCell cell = row.getCell(j);
				System.out.println(cell);
			}
		}
		
		}catch(Exception e) {
		System.out.println("已运行xlRead() : " + e );
		}*/
		
		}		
}
