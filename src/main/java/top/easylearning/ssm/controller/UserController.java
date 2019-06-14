package top.easylearning.ssm.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import top.easylearning.ssm.domain.User;
import top.easylearning.ssm.mapper.UserMapper;
import top.easylearning.ssm.service.UserService;

@Controller
@Scope(value="prototype")
/*
 * Spring Bean的作用域
 * singleton作用域：默认情况下使用，指IOC容器仅存在一个Bean的实例，Bean以
 * 单例的方式存在
 * prototype作用域:指每次从容器中调用Bean时，都返回一个新的实例，即每次调用
 * getBean()时，相当于执行new Bean()操作，默认情况下，Spring容器在创建时不
 * 实例化prototype的Bean
 */

@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(User user,Model model) throws Exception {
		user=userService.checkLogin(user.getUsername(), user.getPassword());
		if(user!=null){
			//model.addAttribute("retCode", 0);
			model.addAttribute(user);
			return "welcome";			
		}
		//model.addAttribute("retCode", 1);
		return "fail";
	}
	
	@RequestMapping(value="/get",method=RequestMethod.POST)
	public String getMess(User user,Model model) throws Exception{
		user = userService.getmessage(user.getId());
		if(user!=null)
		{
			model.addAttribute(user);
			return "getmess";
		}
		return "fail";
		
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String getMess(User user) throws Exception{
		userService.Add(user);
		return "insertOk";
		
	}
}