package allutils.SQLutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import allutils.randomUtils.RandomNumberUtil;

/**
 * 
 * JDBC工具类
 * @author zhangliang
 * @version V1.0
 * 
 */
public class JDBCUtil {
	//ctrl + shift + x  转成大写
	//ctrl + shift + y  转成小写
	private static final String DRIVERNAME;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	/**
	 * 定义四大连接参数
	 */
	static{
		
		/*通过ResourceBundle 专门用来加载properties文件
		ResourceBundle bundle=ResourceBundle.getBundle("文件名称");
		
		通过 bundle.getString(键的名字)
		String value=bundle.getString("url");
	*/
    	
		ResourceBundle bundle=ResourceBundle.getBundle("jdbc");
		DRIVERNAME=bundle.getString("drivername");
		System.out.println("开始尝试连接数据库！");
		URL=bundle.getString("url");
		USERNAME=bundle.getString("user");
		PASSWORD=bundle.getString("password");
		
		
		//mysql连接方式
		/*DRIVERNAME="com.mysql.jdbc.Driver";
		System.out.println("开始尝试连接数据库！");
		URL="jdbc:mysql://localhost:3306/stu";
		USERNAME="root";
		PASSWORD="root";*/
		
		
		//oracle连接方式
		/*DRIVERNAME="oracle.jdbc.OracleDriver";
		System.out.println("开始尝试连接数据库！");
		URL="jdbc:oracle:thin:@localhost:1521:orcl";
		USERNAME="HDMY";
		PASSWORD="HDMY";*/
	}
	
	static{
		try {
			Class.forName(DRIVERNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取链接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		System.out.println("连接成功！");
		return conn;
	}
	
	/**
	 * 释放资源
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void closeResource(Connection conn,Statement st,ResultSet rs){
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 保存数据
	 * @param conn
	 * @param st
	 * @param sql
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void insert(Connection conn,Statement st,String sql) throws ClassNotFoundException, SQLException {
		//执行sql
		int i=st.executeUpdate(sql);
		//处理结果
		if (i>0) {
			System.out.println("保存成功");
		}else{
			System.out.println("保存失败");
		}
		//释放资源
		/*st.close();
		conn.close();*/
	}
	
	/**
	 * 删除数据
	 * @param conn
	 * @param st
	 * @param sql
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void delete(Connection conn,Statement st,String sql) throws ClassNotFoundException, SQLException {

		int i=st.executeUpdate(sql);
		if (i>0) {
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
		
		/*st.close();
		conn.close();*/
	}

	/**
	 * 更新数据
	 * @param conn
	 * @param st
	 * @param sql
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void update(Connection conn,Statement st,String sql) throws ClassNotFoundException, SQLException {
		//执行sql
		int i =st.executeUpdate(sql);
		//处理结果
		if (i>0) {
			//ctrl + alt + ↓  向下复制一行
			System.out.println("更新成功");
		}else{
			//alt +↓ 向下移动一行
			System.out.println("更新失败");
		}
		//释放资源
		/*st.close();
		conn.close();*/
	}

	
	/**
	 * 查询数据
	 * @param conn
	 * @param st
	 * @param rs
	 * @param sql
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static ResultSet getQuery(Connection conn,Statement st, ResultSet rs ,String sql) throws ClassNotFoundException, SQLException {
		 rs=st.executeQuery(sql);
		if (rs!=null) {
			System.out.println("查询成功");
		}
	  /*rs.close();
		st.close();
		conn.close();*/
		return rs;
	}

	
	
	public static void main(String[] args) throws SQLException {
		//保存数据
		/*try {
			Connection conn = JDBCUtil.getConnection();
			Statement st = conn.createStatement();
			//insert(conn, st , "insert into stu values('3','zhangsan')");
			long randomLong = RandomNumberUtil.randomLong(18);
			insert(conn, st , "insert into T_SPA_SAFE_STANDARD(STD_ID,STD_CODE) values("+randomLong+",'zhangsan')");
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		
		//删除数据
		/*try {
			Connection conn = JDBCUtil.getConnection();
			Statement st = conn.createStatement();
			String str="lisi";
			delete(conn, st , "delete from stu where name='"+str+"'");
			
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		
		//更新数据
		/*try {
			Connection conn = JDBCUtil.getConnection();
			Statement st = conn.createStatement();
			String str="zhangsan";
			update(conn, st, "update stu set name='lisi' where name='"+str+"'");
		
			String strs= "lisi";
			String str="zhangsan";
			
			update(conn, st, "update T_SPA_SAFE_STANDARD set STD_CODE='"+strs+"' where STD_CODE='"+str+"'");
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		
		
		//循环替换主键
		try {
			Connection conn = JDBCUtil.getConnection();
			Statement st = conn.createStatement();//用于查询
			Statement sts = conn.createStatement();//用于更新
			ResultSet rs=null;
			//执行查询
			//rs= getQuery(conn, st, rs, "select * from T_SPA_SAFE_STANDARD where STD_CODE='"+"lisi"+"'");
			rs= getQuery(conn, st, rs, "select * from T_SPA_SAFE_STANDARD ");
			
			//执行循环更新
			int num =1;
			while (rs.next()) {
				
				//String stdId=rs.getString(1);
				//int stdId = rs.getInt(1);//获取id,18位数字会报溢出异常
				long stdId = rs.getLong(1);//获取id
				String stdcode=rs.getString(2);//获取code
				System.out.println(stdId+"::"+stdcode);
				long id = RandomNumberUtil.randomLong(18);//生成18位随机数
				
				update(conn, sts, "update T_SPA_SAFE_STANDARD set STD_ID='"+id+"' where STD_ID='"+stdId+"'");
				//update(conn, sts, "update T_SPA_SAFE_STANDARD set STD_ID='"+num+++"' where STD_ID='"+stdId+"'");
			}
			//关闭资源
			rs.close();
			st.close();
			sts.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
		//查询数据
		/*try {
			Connection conn = JDBCUtil.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs=null;
			String str="lisi";
			//rs= getQuery(conn, st, rs, "select * from stu where name='"+str+"'");
			rs= getQuery(conn, st, rs, "select * from T_SPA_SAFE_STANDARD");
			
			while (rs.next()) {
				Object id=rs.getObject(1);
				//String id=rs.getString(1);
				String name=rs.getString(2);
				System.out.println("id:"+id+"\n"+"name:"+name);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/
	}
}
