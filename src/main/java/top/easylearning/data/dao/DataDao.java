package top.easylearning.data.dao;

import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import top.easylearning.data.service.DataService;
import top.easylearning.jdbc.JdbcUtils;

public class DataDao implements DataService {

	private JdbcUtils jdbcUtils;

	public DataDao() {
		jdbcUtils = new JdbcUtils();
	}

	/**
	 * 插入资料信息
	 */
	@Override
	public boolean addData(List<Object> params) {

		boolean flag = false;
		try {
			String sql = "insert into data(id,dataname,dataauther,datacontent,dataimage) values(?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	

	

	/**
	 * 提取资料信息显示到页面
	 */
	@Override
	public List<Map<String, Object>> listData(String dataname,int start,int end) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from data where(1=1)";

		// 使用该方法进行字符串的连接，将比String更加节约内容，例如应用于数据库SQL语句的连接，
		// 例如：
		// StringBuffer sb = new StringBuffer();
		// String user = “test”;
		// String pwd = “123”;
		// sb.append(“select * from userInfo where username=“)
		// .append(user).append(“ and pwd=”) .append(pwd);
		// 这样对象sb的值就是字符串“select * from		
		
		//已将sql当做参数传入
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		// 输入文章标题，模糊查询，筛选出所有标题
		if (dataname != null) {
			buffer.append(" and dataname like ? ");//sql语句后追加
			params.add("%" + dataname + "%");
		}
		//追加分页功能
		buffer.append("limit ?,?");
		params.add(start);
		params.add(end);
		
		
		try {
			jdbcUtils.getConnection();
			//将StringBuffer转换为Stirng类型才能识别
			list = jdbcUtils.findMoreResult(buffer.toString(), params);//sql语句，占位符
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}

		return list;
	}

	/**
	 * 得到记录总条数
	 */
	@Override
	public int getItemCount() {
		int result = 0;
		Map<String, Object> map = null;
		String sql = "select count(*) mycount from data";
		
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, null);
			result = Integer.parseInt(map.get("mycount").toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		
		
		
		return result;
	}

	@Override
	public boolean delData(String[] ids) {
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			//[ids.length]表明用户选中复选框后对应没个复选框id的值
			String[] sql = new String[ids.length];
			if (ids!=null) {
				for(int i=0;i<ids.length;i++){
					sql[i] = "delete from data where id='" + ids[i] + "'";
				}
			}
			flag = jdbcUtils.deleteByBatch(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUtils.releaseConn();
		}
		
		return flag;
	}

	@Override
	public Map<String, Object> viewData(String id) {
		
		Map<String, Object> map = null;
		try {
			String sql = "select * from data where id = ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(id);
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUtils.releaseConn();
		}
		
		return map;
		
	}

}
