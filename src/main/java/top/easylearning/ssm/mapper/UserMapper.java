package top.easylearning.ssm.mapper;

import top.easylearning.ssm.domain.User;

public interface UserMapper {
	//第五步：建立UserMapper接口
	public User getUserByName(String username);
	
	public User getUserById(int id);
	
	public void addUser(User user);
}
