package top.easylearning.register.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.easylearning.register.dao.RegisterDao;
import top.easylearning.register.service.RegisterService;




@WebServlet("/servlet/RegisterAction")
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RegisterService service;
	
    public RegisterAction() {
        super();
    }

    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	//业务逻辑层实例化对象赋值给接口层申明的对象进行使用
    	service = new RegisterDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//获取上下文路径
		String path = request.getContextPath();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//拿的是JSP中name为“usrename”的值
		String username = request.getParameter("username");
		String password = request.getParameter("password1");
		String realname = request.getParameter("realname");
		String validate = request.getParameter("validate");
		
		List<Object> params = new ArrayList<Object>();
		params.add(username);
		params.add(password);
		params.add(realname);
		params.add(validate);
		//registerUser是接口中定义的方法，Dao层进行的实现；返回值为布尔型
		boolean flag = service.registerUser(params);
		
		if (flag) {
			
			//重定向
			
			response.sendRedirect(path+"/sys_login/index.jsp");
		
		}
		
		
		
		out.flush();
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


