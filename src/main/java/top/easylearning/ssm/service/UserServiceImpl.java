package top.easylearning.ssm.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.easylearning.ssm.dao.UserDao;
import top.easylearning.ssm.domain.User;
@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Override
	public User checkLogin(String username, String password) {
		User user=userDao.findUserByName(username);
		if(user!=null&&user.getPassword().equals(password)){
			return user;
		}
		return null;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getmessage(int id) {
		User user = userDao.findUserById(id);
		if(user!=null){
		return user;
		}
		return null;
	}

	@Override
	public void Add(User user) {
		// TODO Auto-generated method stub
		userDao.Adduser(user);
	}


	

}
