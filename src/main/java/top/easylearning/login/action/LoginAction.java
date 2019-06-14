package top.easylearning.login.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.easylearning.login.dao.LoginDao;
import top.easylearning.login.service.LoginService;




@WebServlet("/servlet/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private LoginService service;   

    public LoginAction() {
        super();
        
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	service = new LoginDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path= request.getContextPath();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String pswd = request.getParameter("password");
		//LoginService中参数，返回值布尔类型
		List<Object> params = new ArrayList<Object>();
		params.add(username);
		params.add(pswd);
		//LoginService的对象service去调用DAO层的接口方法login，传入参数，操作数据库,返回值为布尔类型
		boolean flag = service.login(params);
		if (flag) {
			//第一个参数用于后面的getAtrributeXXX,第二个参数是表单中的name值，在这里是get到表单中那么值,赋给了当前的username
			///easylearningSSM/src/main/webapp/sys/public/top.jsp
			request.getSession().setAttribute("username", username);
			System.out.println("LoginAction中的flag:"+flag);
			//重定向到主界面
			response.sendRedirect(path+"/sys/index.jsp");
		}else {
			response.sendRedirect(path+"/sys/fail.jsp");
		}
		out.flush();
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
