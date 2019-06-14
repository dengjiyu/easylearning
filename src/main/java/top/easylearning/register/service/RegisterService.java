package top.easylearning.register.service;

import java.util.List;

public interface RegisterService {

	//完成用户的注册功能
/**
 * 
 * 参数：封装的JDBC中的对于增删改操作的占位符
 * @return
 */
	public boolean registerUser(List<Object> params);
}

