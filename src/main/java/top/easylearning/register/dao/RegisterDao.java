package top.easylearning.register.dao;

import java.util.List;

import top.easylearning.jdbc.JdbcUtils;
import top.easylearning.register.service.RegisterService;

public class RegisterDao implements RegisterService {
	
	private JdbcUtils utils = null;
	
	public RegisterDao() {
		//实例化一个封装好JDBC的对象
		utils = new JdbcUtils();
	}

	/**
	 * 完成了对用户注册Dao的编写
	 */
	@Override
	public boolean registerUser(List<Object> params) {
		boolean flag = false;
		String sql = "insert into userinfo(username,password,realname,validate) values(?,?,?,?)";
		try {
			//得到数据库链接
			utils.getConnection();
			/*
			 * 关键：
			 * 1.使用封装好的JDBC，传入sql语句与占位符进行与数据库交互操作
			 * 2.调用写好的JdbcUtils中写好的方法，从而直接进行增删改，返回值为布尔类型，因此定义flag来进行接收调用方法返回的值
			 */
			flag = utils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			utils.releaseConn();
		}
		
		
		return flag;
	}

}
