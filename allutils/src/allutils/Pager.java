package allutils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

/**
 * 
 * 分页工具类
 * @author zhangliang
 * @version V1.0
 * 
 */
public class Pager {

	// 当前页数
	private int page;

	// 每页显示记录数
	private int perRows;
	
	// 总记录数
	private int records;

	// 总记录数
	private int total;

	// List集合
	private List rows;
	
	//排序字段
	private String sort;
	
	//排序方式
	private String order;

	//车辆总数
	private int carTotal;
	
	public int getCarTotal() {
		return carTotal;
	}

	public void setCarTotal(int carTotal) {
		this.carTotal = carTotal;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPerRows() {
		return perRows;
	}

	public void setPerRows(int perRows) {
		this.perRows = perRows;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	/**
	 * 给没有加分页的sql语句加上分页的部分
	 * 
	 * @param page
	 *            当前页号
	 * @param rows
	 *            每页显示的数据条数
	 * @param sql
	 *            没加分页前的sql语句
	 * @return
	 */
	public static final String getPagerStr(int page, int rows, String sql) {
		StringBuffer rltSql = new StringBuffer();
		rltSql.append("select * from ");
		rltSql.append("( select rownum r, oldtb.* from (");
		rltSql.append(sql);
		rltSql.append(") oldtb ");
		rltSql.append(" where rownum <= (" + page + " * " + rows
				+ ")) where r >  ((" + page + "-1) * " + rows + ")");
		return rltSql.toString();
	}

	/**
	 * 通过原来的sql生成取总条数的sql
	 * 
	 * @param sql
	 *            原来的sql
	 * @return
	 */
	public static final String getTotalStr(String sql) {
		StringBuffer rltSql = new StringBuffer();
		rltSql.append("select count(*) data_total from (");
		rltSql.append(sql);
		rltSql.append(" )  tb");
		return rltSql.toString();
	}
	
	
	public static String getCarTotalStr(String sql) {
		StringBuffer rltSql = new StringBuffer();
		rltSql.append("select count(*) data_total from (");
		rltSql.append(sql);
		rltSql.append(" )  tb ");
		return rltSql.toString();
	}

	/**
	 * 通过原来的sql生成取总条数的sql
	 * 
	 * @param sql
	 *            原来的sql
	 * @return
	 */
	public static final String getTotalStrHql(String hql) {
		int orderindex=hql.toLowerCase().lastIndexOf("order by ");
		int fromindex=hql.toLowerCase().indexOf("from");
		StringBuffer rltSql = new StringBuffer();
		rltSql.append("select count(*) ");
		if(orderindex!=-1){
			rltSql.append(hql.substring(fromindex,orderindex));
		}else{
			rltSql.append(hql.substring(fromindex));
		}
		return rltSql.toString();
	}
	
	/**
	 * 为list集合分页
	 * 
	 * @param list
	 * @param currentPage
	 * @param pageRows
	 * @return
	 */
	public static final String getGridJson(List list, String currentPage,
			String pageRows) {
		int totalRecord = list.size(); // 总记录数
		int totalPage = totalRecord % Integer.parseInt(pageRows) == 0 ? totalRecord
				/ Integer.parseInt(pageRows)
				: totalRecord / Integer.parseInt(pageRows) + 1; // 计算总页数
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("currentPage", Integer.parseInt(currentPage)); // 当前页
		jsonMap.put("totalPages", totalPage); // 总页数
		jsonMap.put("totalRecords", totalRecord);// 总记录数

		int fromIndex = Integer.parseInt(pageRows)
				* (Integer.parseInt(currentPage) - 1);
		int toIndex = 0;
		if (Integer.parseInt(pageRows) * Integer.parseInt(currentPage) <= totalRecord) {
			toIndex = Integer.parseInt(pageRows)
					* Integer.parseInt(currentPage);
		} else {
			toIndex = totalRecord;
		}

		jsonMap.put("list", list.subList(fromIndex, toIndex)); // 需显示的数据行
		JSONArray jsonarray = JSONArray.fromObject(jsonMap);
		return jsonarray.toString().substring(1,
				jsonarray.toString().length() - 1);
	}

	/**
	 * 
	 * 在sql后面自动追加where条件
	 * 
	 * @param fieldname
	 *            查询字段名
	 * @param fieldvalue
	 *            查询字段值
	 * @param isLike
	 *            是否模糊查询
	 */
	public final String appendWhereSql(String fieldname, Object fieldvalue,
			boolean isLike) {
		String whereSql = "";
		if (fieldvalue != null) {
			if (fieldvalue.getClass() == String.class) {
				if (!fieldvalue.toString().equals("")) {
					if (!isLike) {
						whereSql = "and t." + fieldname + " = '" + fieldvalue
								+ "' ";
					} else {
						whereSql = "and t." + fieldname + " like '%"
								+ fieldvalue + "%' ";
					}
				}
			} else {
				whereSql = "and t." + fieldname + " = " + fieldvalue + " ";
			}
		}
		return whereSql;
	}

	/**
	 * 表不加别名的sql条件
	 * 
	 * @param fieldname
	 * @param fieldvalue
	 * @param isLike
	 * @return
	 */
	public static final String appendWhereNewSql(String fieldname,
			Object fieldvalue, boolean isLike) {
		String whereSql = "";
		if (fieldvalue != null) {
			if (fieldvalue.getClass() == String.class) {
				if (!fieldvalue.toString().equals("")) {
					if (!isLike) {
						whereSql = "and " + fieldname + " = '" + fieldvalue
								+ "' ";
					} else {
						whereSql = "and " + fieldname + " like '%" + fieldvalue
								+ "%' ";
					}
				}
			} else {
				whereSql = "and " + fieldname + " = " + fieldvalue + " ";
			}
		}
		return whereSql;
	}

	/**
	 * 
	 * 在sql后面自动追加数组条件
	 * 
	 * @param string
	 * @param array
	 * @return
	 */
	public String appendArraySql(String fieldname, String htStateCode) {
		String whereSql = "";
		if (htStateCode != null && !"".equals(htStateCode)) {
			String[] array = htStateCode.split(",");
			if (array.length > 0) {
				whereSql = "and (";
				for (int i = 0; i < array.length; i++) {
					if("null".equals(array[i])){
						whereSql += "t." + fieldname + " is null ";
					}else{
						whereSql += "t." + fieldname + " = '" + array[i] + "' ";
					}
					

					if (i != array.length - 1) {
						whereSql += " or ";
					}
				}
				whereSql += ") ";
			}
		}
		return whereSql;
	}
	
	public String stringToColumn(){
		String result = "";
		int start = 0;
		int end = 0;
		if(sort != null && !sort.equals("")){
			for(int i=0;i<sort.length();i++){ 
				char ch = sort.charAt(i); 
				end = i;
				if(ch>='A' && ch<='Z'){
					result += sort.substring(start, end);
					result += "_";
					start = i;
				}
			}
			result += sort.substring(start, end+1);

		}
		return result;
	}
	
	
	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public int getTotalPage() {
		int totalPage = 0;
		if (perRows != 0) {
			totalPage = records / perRows;
			if (records % perRows > 0) {
				totalPage += 1;
			}
		}
		return totalPage;
	}
	
	/**
	 * 通过表名和orgid的串（多个时用,分隔）
	 * @param tbcolname 字段名，如sql中表起别名了，字段名也要带上表别名。（如 t.coll_code）
	 * @param orgids 组织机构ID串（如：B2,B2S,B2S001）
	 * @return 返回需要拼接到 where条件后通过组织机构过滤数据的串。
	 */
	public static String getDateFilterWhere(String tbcolname,String orgids){
		StringBuffer whereStr=new StringBuffer();		
		if(orgids == null || "".equals(orgids)){
			whereStr.append("");
		}else if("HQ".equals(orgids)){
			whereStr.append("");
		}else{
			String [] orgidArray=orgids.split(",");
			for(int i=0;i<orgidArray.length;i++){
				if(i==0){
					whereStr.append(" and ("+tbcolname+"='"+orgidArray[i]+"' ");
				}else{
					whereStr.append(" or "+tbcolname+ "='"+orgidArray[i]+"'");
				}
			}
			whereStr.append(") ");			
		}		
		return whereStr.toString();
	}


}
