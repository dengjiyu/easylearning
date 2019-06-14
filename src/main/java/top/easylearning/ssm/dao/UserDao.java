package top.easylearning.ssm.dao;

import top.easylearning.ssm.domain.User;

public interface UserDao {

	public User findUserByName(String username) ;
	
	public User findUserById(int id);
	
	public void Adduser(User user);
}
