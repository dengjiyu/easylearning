/*package djy.data.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



import djy.data.dao.DataDao;
import djy.data.service.DataService;
import djy.util.DividePage;
import djy.util.UUIDTools;



@WebServlet("/servlet/DataAction")
public class DataAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DataService service;
	
    public DataAction() {
        super();
    }

	@Override
	public void init() throws ServletException {
		super.init();
		service = new DataDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// action_flag:是2_1_5tj.jsp中传的参数、th.action="<%=path%>/servlet/DataAction?action_flag=add";
		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("add")) {
			addData(request, response);
		
		}else if (action_flag.equals("list")) {			
			listData(request, response);			
		}else if (action_flag.equals("del")) {
			delData(request, response);	
		}else if (action_flag.equals("view")) {
			viewData(request, response);	
		}

		out.flush();
		out.close();
	}



	private void viewData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Map<String, Object> map = service.viewData(id);
		request.setAttribute("map", map);
		
		request.getRequestDispatcher("/data_details.jsp").forward(request, response);
		
	
		
	}

	private void delData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		//获得选中的复选框的值
		String[] ids = request.getParameterValues("ids");
		boolean flag = service.delData(ids);
		if (flag) {
			response.sendRedirect(path+"/servlet/DataAction?action_flag=list");

		}
		
	}

	private void listData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String path = request.getContextPath();
		//接收用户的查询文章标题
		String dataname = request.getParameter("dataname");
		*//**************开始分页操作**************//*
		int recordCount = service.getItemCount();//获得记录的总条数
		int currentPage = 1;//当前页是第一页
		String pageNum = request.getParameter("pageNum");
		if (pageNum != null) {
			currentPage = Integer.parseInt(pageNum);
		}
		//参数：5个一分；总页数；当前页
		DividePage pUtil = new DividePage(10, recordCount,currentPage);
		int start = pUtil.getFromIndex();
		int  end = pUtil.getToIndex();
		
		
		*//**************分页操作**************//*		
		List<Map<String, Object>> list = service.listData(dataname,start,end);
		//已经进行分页之后的数据集合
		request.setAttribute("pUtil", pUtil);
		request.setAttribute("listdata", list);
		request.setAttribute("dataname", dataname);
		request.getRequestDispatcher("/data_show.jsp").forward(request, response);
		
		
		
	}

	private void addData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 表单含有文件要提交
		String path = request.getContextPath();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(3 * 1024 * 1024);
		servletFileUpload.setSizeMax(6 * 1024 * 1024);
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		params.add(UUIDTools.getUUID());
		try {
			// 解析request的请求
			list = servletFileUpload.parseRequest(request);
			// 取出所有表单的值:判断非文本字段和文本字段
			for (FileItem fileItem : list) {
				// 如果是文本字段
				if (fileItem.isFormField()) {
					if (fileItem.getFieldName().equals("dataname")) {
						params.add(fileItem.getString("utf-8"));
					}
					if (fileItem.getFieldName().equals("dataauther")) {
						params.add(fileItem.getString("utf-8"));
					}
					if (fileItem.getFieldName().equals("datacontent")) {
						params.add(fileItem.getString("utf-8"));
					}
				} else {
					try {
						
						String image = fileItem.getName();
						params.add(image);
						String upload_path = request.getSession().getServletContext().getRealPath("/upload");
						
						 *上传到图片位置：
						 *--->>F:\小学期\ENV\ENV\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\xiaofengProject\
						 *			upload
						 *eclipse运行web项目后, 默认保存到 workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps这个目录下
						 *在这里会生成web的一些临时文件，如jsp临时文件，项目WebContent下的文件在这里都会生成。
						 *注意：
						 *只有当启动tomcat的时候这些临时文件及临时文件中的图片才会生成，如果一旦remove掉项目或者没有启动tomcat,这些临时文件夹中是没有的
						 *指定路径不存在：在对应目录下建文件夹
						 
						System.out.println("---->>" + upload_path);
						
						File real_path = new File(upload_path + "/" + image);
						fileItem.write(real_path);
						boolean flag = service.addData(params);
						if (flag) {// true
							System.out.println("上传成功！！！");
							response.sendRedirect(path+"/servlet/DataAction?action_flag=list");
						}
						// 把数据插入到数据库中

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
*/


package top.easylearning.data.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



import top.easylearning.data.dao.DataDao;
import top.easylearning.data.service.DataService;
import top.easylearning.util.DividePage;
import top.easylearning.util.UUIDTools;



@WebServlet("/servlet/DataAction")
public class DataAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DataService service;
	
    public DataAction() {
        super();
    }

	@Override
	public void init() throws ServletException {
		super.init();
		service = new DataDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// action_flag:是2_1_5tj.jsp中传的参数、th.action="<%=path%>/servlet/DataAction?action_flag=add";
		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("add")) {
			addData(request, response);
		
		}else if (action_flag.equals("list")) {			
			listData(request, response);			
		}else if (action_flag.equals("del")) {
			delData(request, response);	
		}else if (action_flag.equals("view")) {
			viewData(request, response);	
		}

		out.flush();
		out.close();
	}



	private void viewData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Map<String, Object> map = service.viewData(id);
		request.setAttribute("map", map);
		
		request.getRequestDispatcher("/data_details.jsp").forward(request, response);
		
	
		
	}

	private void delData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		//获得选中的复选框的值
		String[] ids = request.getParameterValues("ids");
		boolean flag = service.delData(ids);
		if (flag) {
			response.sendRedirect(path+"/servlet/DataAction?action_flag=list");

		}
		
	}

	private void listData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String path = request.getContextPath();
		//接收用户的查询文章标题
		String dataname = request.getParameter("dataname");
		/**************开始分页操作**************/
		int recordCount = service.getItemCount();//获得记录的总条数
		int currentPage = 1;//当前页是第一页
		String pageNum = request.getParameter("pageNum");
		if (pageNum != null) {
			currentPage = Integer.parseInt(pageNum);
		}
		//参数：5个一分；总页数；当前页
		DividePage pUtil = new DividePage(10, recordCount,currentPage);
		int start = pUtil.getFromIndex();
		int  end = pUtil.getToIndex();
		
		
		/**************分页操作**************/		
		List<Map<String, Object>> list = service.listData(dataname,start,end);
		//已经进行分页之后的数据集合
		request.setAttribute("pUtil", pUtil);
		request.setAttribute("listdata", list);
		request.setAttribute("dataname", dataname);
		request.getRequestDispatcher("/data_show.jsp").forward(request, response);
		
		
		
	}

	private void addData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 表单含有文件要提交
		String path = request.getContextPath();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(3 * 1024 * 1024);
		servletFileUpload.setSizeMax(6 * 1024 * 1024);
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		params.add(UUIDTools.getUUID());
		try {
			// 解析request的请求
			list = servletFileUpload.parseRequest(request);
			// 取出所有表单的值:判断非文本字段和文本字段
			for (FileItem fileItem : list) {
				// 如果是文本字段
				if (fileItem.isFormField()) {
					if (fileItem.getFieldName().equals("dataname")) {
						params.add(fileItem.getString("utf-8"));
					}
					if (fileItem.getFieldName().equals("dataauther")) {
						params.add(fileItem.getString("utf-8"));
					}
					if (fileItem.getFieldName().equals("datacontent")) {
						params.add(fileItem.getString("utf-8"));
					}
				} else {
					try {
						
						String image = fileItem.getName();
						params.add(image);
						String upload_path = request.getSession().getServletContext().getRealPath("/upload");
						/*
						 *上传到图片位置：
						 *--->>F:\小学期\ENV\ENV\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\xiaofengProject\
						 *			upload
						 *eclipse运行web项目后, 默认保存到 workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps这个目录下
						 *在这里会生成web的一些临时文件，如jsp临时文件，项目WebContent下的文件在这里都会生成。
						 *注意：
						 *只有当启动tomcat的时候这些临时文件及临时文件中的图片才会生成，如果一旦remove掉项目或者没有启动tomcat,这些临时文件夹中是没有的
						 *指定路径不存在：在对应目录下建文件夹
						 */
						System.out.println("---->>" + upload_path);
						
						File real_path = new File(upload_path + "/" + image);
						fileItem.write(real_path);
						boolean flag = service.addData(params);
						if (flag) {// true
							System.out.println("上传成功！！！");
							response.sendRedirect(path+"/servlet/DataAction?action_flag=list");
						}
						// 把数据插入到数据库中

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
