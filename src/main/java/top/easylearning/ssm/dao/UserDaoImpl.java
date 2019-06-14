package top.easylearning.ssm.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import top.easylearning.ssm.domain.User;
import top.easylearning.ssm.mapper.UserMapper;
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	//第六步：
	//注解引用Mapper类资源
	@Resource(name="userMapper")
	private UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}
	
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public User findUserByName(String username) {
		
		return userMapper.getUserByName(username);
	}

	@Override
	public User findUserById(int id) {
		
		return userMapper.getUserById(id);
	}

	@Override
	public void Adduser(User user) {
		// TODO Auto-generated method stub
		userMapper.addUser(user);
	}


	
	
}
