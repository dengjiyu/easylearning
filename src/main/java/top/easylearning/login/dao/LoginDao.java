package top.easylearning.login.dao;

import java.util.List;
import java.util.Map;

import top.easylearning.jdbc.JdbcUtils;
import top.easylearning.login.service.LoginService;
/*
 * action:表现出，dao：持久层，service:业务层
 */
public class LoginDao implements LoginService {

	private JdbcUtils jdbcUtils = null;
	
	public  LoginDao() {
		//实例化封装好的JDBC框架
		jdbcUtils = new JdbcUtils();
	}

	@Override
	public boolean login(List<Object> params) {
		boolean flag = false;
		String sql = "select * from userinfo where username=? and password=?";
		try {
			  jdbcUtils.getConnection();
			  /*
			   * JDBC封装的方法findSimpleResult();方法作用：查询返回单条记录,返回值为 Map<String, Object>
			   * 在单条记录查询中返回的一定不为空，所以采用!map.isEmpty() 从而达到返回flag的目的
			   */
			  Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			  //逻辑非运算，不为空就是有值
			  flag = !map.isEmpty() ? true : false;
		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

}
