package top.easylearning.jdbc;

import java.beans.PropertyVetoException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {

	/**
		Class.forName("com.mysql.jdbc.Driver");
		String dbUrl = "jdbc:mysql://localhost:3306/mealsystem";
    	Connection con = DriverManager.getConnection(dbUrl,"root","root");
    	Statement stmt = con.createStatement();
    	String sql ="select * from user where userName like '%"+search+"%'";
    	ResultSet rs = stmt.executeQuery(sql);
    	while(rs.next()){
	 */
	// 表示定义数据库的用户名
//	private final String USERNAME = "root";
	// 定义数据库的密码
//	private final String PASSWORD = "root";
	// 定义数据库的驱动信息
//	private final String DRIVER = "com.mysql.jdbc.Driver";
	// 定义访问数据库的地址
//	private final String URL = "jdbc:mysql://localhost:3306/easylearning";
	// 定义数据库的链接
	private Connection connection;
	// 定义sql语句的执行对象
	private PreparedStatement pstmt;
	// 定义查询返回的结果集合
	private ResultSet resultSet;
	// 实现批处理操作的功能
	private Statement stmt;

	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	
//	public JdbcUtils() {
//		try {
//			Class.forName(DRIVER);
//			System.out.println("注册驱动成功!!");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}

	// 定义获得数据库的链接
	public Connection getConnection() {
		 try {
	            connection = ds.getConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return connection;
	}

	//批处理操作
	public boolean deleteByBatch(String[] sql) throws SQLException{
		boolean flag = false;
		stmt = connection.createStatement();
		if(sql!=null){
			//i<sql.length,不要用长度判断
			for(int i=0;i<sql.length;i++){
				stmt.addBatch(sql[i]);
			}
		}
		int[] count = stmt.executeBatch();
		if(count!=null){
			flag = true;
		}
		return flag;
	}
	/** 更新数据库，包括增加记录、删除记录、改动某个记录三个功能。
	 * 完成对数据库的表的添加、删除、修改的操作
	 * @param sql ：将写好的sql语句传入本方法中
	 * @param params ：传进占位符
	 * @return
	 * @throws SQLException
	 */
	public boolean updateByPreparedStatement(String sql, List<Object> params)
			throws SQLException {
		boolean flag = false;
		int result = -1;// 表示当用户执行添加删除和修改的时候所影响数据库的行数
		pstmt = connection.prepareStatement(sql);//执行传入的SQL语句
		int index = 1;//表示占位符第一个位置
		
		if (params != null && !params.isEmpty()) {//判断所填充的占位符里面是否有值
			//循环填充占位符
			for (int i = 0; i < params.size(); i++) {
				//setObject():PreparedStatement类中的方法，此方法表示占位符先用Object类型进行占用
				pstmt.setObject(index++, params.get(i));
			}
		}
		//更新所影响数据库的行数的方法
		result = pstmt.executeUpdate();
		flag = result > 0 ? true : false;
		return flag;
	}

	/**
	 * 查询返回单条记录
	 * 查询单条记录，传进去的是一个List<Object>参数填充占位符，返回的是一个Map<String, Object>.一个Map对应一条完整的记录，String对应属性名，Object是属性值。
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> findSimpleResult(String sql, List<Object> params)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();// 返回查询结果
		ResultSetMetaData metaData = resultSet.getMetaData();
		int col_len = metaData.getColumnCount();// 获得列的名称
		while (resultSet.next()) {
			for (int i = 0; i < col_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
		}
		return map;
	}

	/**
	 * 查询返回多行记录
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findMoreResult(String sql,
			List<Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		//prepareStatement:更有效率，Statement stmt = con.createStatement() ResultSet rs = stmt.executeQuery(sql);
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}
	
	//扩展两个反射来查询的函数。
	// jdbc的封装可以用反射机制来封装：利用反射查询单个记录
	public <T> T findSimpleRefResult(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		T resultObject = null;
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			// 通过反射机制创建实例
			resultObject = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true);// 打开javabean的访问private权限
				field.set(resultObject, cols_value);
			}
		}
		return resultObject;
	}

	/**
	 * 通过反射机制访问数据库:利用反射查询多个记录
	 * @param <T>
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findMoreRefResult(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		List<T> list = new ArrayList<T>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			T resultObject = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true);
				field.set(resultObject, cols_value);
			}
			list.add(resultObject);
		}
		return list;
	}

	public void releaseConn() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
