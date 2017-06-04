package allutils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


/**
 * 
 * 描述该文件做什么 工具类
 * @author zhangliang
 * @version V1.0
 * 
 */
@SuppressWarnings("deprecation")
public class privateUtil {
	// 获取当前时间带十分秒的日期
	public static Timestamp getNowTime() {
		return new Timestamp(Calendar.getInstance().get(Calendar.YEAR) - 1900,
				Calendar.getInstance().get(Calendar.MONTH), Calendar
						.getInstance().get(Calendar.DATE), Calendar
						.getInstance().get(Calendar.HOUR), Calendar
						.getInstance().get(Calendar.MINUTE), Calendar
						.getInstance().get(Calendar.SECOND), 0);
	}

	// 根据类型返回对应的实例，并给属性赋值map.put("FileName" , value)
	public static <T> T getNewConserve(Map map, Class clzz)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, InstantiationException,
			ClassNotFoundException {
		Object newInstance = null;
		if (clzz == null) {
			return null;
		} else {
			Class entityBean = Class.forName(clzz.getCanonicalName());
			newInstance = entityBean.newInstance();
			Method[] allMethods = entityBean.getDeclaredMethods();
			for (Method method : allMethods) {
				if ((method.getName().substring(0, 3)).equalsIgnoreCase("set")) {
					String fieldName = method.getName().substring(3);
					for (Object strFileName : map.keySet()) {
						if (fieldName.equalsIgnoreCase((String) strFileName)) {
							method.invoke(newInstance,
									map.get((String) strFileName));
						}
					}
				}
			}
		}
		return (T) newInstance;
	}

	/**
	 * 此方法把List集合中的Object类型的数组，装换成Clzz类型的对象
	 * 只要sql/hql语句中查出的列和Clzz对象中的属性能对应上(不限个数)，就能转换成clzz类型的 对象实例，并给予属性赋值。
	 * 适用连表查询出来的字段，转换成vo对象 如：List<Object[]> arrayObj =
	 * query.createSqlQuery(sql).list(); List<vo> vos =
	 * privateUtil.getMapperBean(sql , arrayObj , vo.class); 不需要映射文件。（只适用查询语句 ，
	 * 如：select...各种字段... from..... 并且属性和列名必须符合命名规范）;
	 * 
	 * @throws Exception
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * 
	 **/
	public static <T> List<T> getMapperBean(String sql, List<Object[]> obj,
			Class clzz) throws Exception {
		if (clzz != null) {
			List<T> lts = new ArrayList();
			try {
				Class entityClass = Class.forName(clzz.getCanonicalName());
				Object newBean = entityClass.newInstance();
				if (obj == null || obj.size() == 0) {
					lts.add((T) newBean);
					return lts;
				}
				if (sql == null || "".equals(sql)) {
					throw new Exception("sql不能为空");
				}
				Method[] methods = entityClass.getDeclaredMethods();
				String[] columnName = getColumnName(sql);
				for (Object t : obj) {
					Object entityBean = entityClass.newInstance();
					executeMethod(methods, columnName, t, entityBean);
					lts.add((T) entityBean);
				}
			} catch (Exception e) {
				throw e;
			}
			return lts;
		} else {
			return null;
		}
	}

	// 把sql对应的列转换成属性
	private static String[] getColumnName(String sql) throws Exception {
		try {
			if (sql != null && !"".equals(sql)) {
				if (sql.contains("from") && sql.contains("select")) {
					int endIndex = sql.indexOf("from");
					int startIndex = sql.indexOf(" ");
					sql = sql.substring(startIndex, endIndex).trim();
					String[] strFiled = null;
					String[] fileName = null;
					if (sql.contains(",")) {
						strFiled = sql.split(", to");
						fileName = new String[strFiled.length];
						for (int i = 0; i < strFiled.length; i++) {
							fileName[i] = constructorFileName(strFiled[i]
									.trim());
						}
						return fileName;
					} else {
						// sql =
						// sql.contains(".")?sql.substring(sql.indexOf(".")+1):sql;
						String newField = constructorFileName(sql.trim());
						strFiled = new String[] { newField };
						return strFiled;
					}
				} else {
					throw new Exception("sql传入有误，只适用用查询语句");
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	// 构造属性
	private static String constructorFileName(String fileName) throws Exception {
		if (fileName.contains(".") || fileName.contains("as")
				|| fileName.contains(" ")) {
			if (fileName.contains(".")) {
				fileName = fileName.substring(fileName.indexOf(".") + 1);
			}
			if (fileName.contains(" ") || fileName.contains("as")) {
				fileName = fileName.substring(fileName.lastIndexOf(" ") + 1);
			}
		}
		if (!fileName.contains("_")) {
			return new StringBuilder()
					.append(Character.toUpperCase(fileName.charAt(0)))
					.append(fileName.substring(1)).toString();
		} else {
			fileName = fileName.replaceAll("_", ",").toLowerCase();
			String[] filed = fileName.split(",");
			String newFiled = "";
			for (int i = 0; i < filed.length; i++) {
				if (i != 0) {
					newFiled += (filed[i].substring(0, 1).toUpperCase())
							+ filed[i].substring(1);
				} else {
					newFiled += filed[0];
				}
			}
			return newFiled;
		}
	}

	// 根据对应的属性执行对应的set方法（赋值）
	private static <T> void executeMethod(Method[] methods,
			String[] columnName, T targ, Object entityBean) throws Exception,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		for (Method method : methods) {
			if ((method.getName().substring(0, 3)).equalsIgnoreCase("set")) {
				String fieldName = method.getName().substring(3);
				for (int i = 0; i < columnName.length; i++) {
					if (fieldName.equalsIgnoreCase(columnName[i])) {
						try {
							if (targ.getClass() == Object.class) {
								method.invoke(entityBean, targ);
							}
							if (targ.getClass() == Object[].class) {
								Object[] flag = (Object[]) targ;
								method.invoke(entityBean, flag[i]);
							}
						} catch (Exception e) {
							throw new Exception("属性值有误或类型不匹配");
						}
					}
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {/*
		String sql = "select EN_NAME , EN_TYPE_NAME FROM T_BASE_ENINFO where EN_TYPE_NAME = '场垃圾处理率'";
		Object[] objs = new Object[] { "oi9_love",
				"yyyyy"};
		Object[] objs1 = new Object[] { "oi9_love1","天科1"};
		Object[] objs2 = new Object[] { "oi9_love2","天科2"};
		List li = new ArrayList();
		li.add(objs);
		li.add(objs1);
		li.add(objs2);
		List<EnNameValueVo> llll = privateUtil.getMapperBean(sql, li, EnNameValueVo.class);
		for (EnNameValueVo vo : llll) {
			System.out.println(vo.getEnName());
		}
	*/}
}
