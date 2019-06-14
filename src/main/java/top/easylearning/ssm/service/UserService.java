package top.easylearning.ssm.service;

import top.easylearning.ssm.domain.User;

public interface UserService {
	
	public User checkLogin(String username,String password);
	
	public User getmessage(int id);
	
	public void Add(User user);
}
