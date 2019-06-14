package top.easylearning.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {

	public MyFilter() {

	}

	/*
	 * 过滤器初始化方法，可以读取xml中过滤的信息，Tomcat启动的时候就会调用这个方法
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/*
	 * 过滤器核心方法：当用户请求访问时XML关联的URL时，Web容器将先调用过滤器的doFilter方法(non-Javadoc)
	 * 因为doFilter不是Serlvet，参数中传入的是ServletRequest类型的request，
	 * 而不是HttpServletRequest类型的request
	 * 所以没有内置对象request,response,因此需要我们先实例出来才可以进行使用
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 过滤用户的请求，判断是否没有登录就进入到sys/index.jsp中进行操作
		// 将doFilter中的参数request进行HttpServletRequest封装，（过滤器过滤的index.jsp会传回这个参数）
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String path = httpServletRequest.getContextPath();
		// 为防止过滤后出现一些乱码问题，应写设置编码格式
		httpServletRequest.setCharacterEncoding("utf-8");
		httpServletResponse.setCharacterEncoding("utf-8");
		/*
		 * 链式编程:
		 * HttpSession session = httpServletRequest.getSession(); 
		 * String username = (String) session.getAttribute("username");
		 * 重点：
		 * 因为username是用session进行存取的，所以测试的时候如果之前登陆过，那么是无法看出过滤器是否生效的，
		 * 应当清一下浏览器或者使用其他浏览器进行打开
		 */
		String username = (String) httpServletRequest.getSession().getAttribute("username");
		if (username == null) {
			httpServletResponse.sendRedirect(path + "/sys_login/index.jsp");
		}
		chain.doFilter(httpServletRequest, httpServletResponse);

	}

	@Override
	public void destroy() {

	}

}
