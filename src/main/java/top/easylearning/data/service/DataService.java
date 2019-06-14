package top.easylearning.data.service;

import java.util.List;
import java.util.Map;

import org.omg.CORBA.INTERNAL;

public interface DataService {

	//添加资料、图片
	public boolean addData(List<Object> params);
	
	//提取所有产品的信息,注意导包：import java.util.Map;
	public List<Map<String, Object>> listData(String dataname,int start,int end);
	
	//得到分页记录的总条数
	public int getItemCount();
	
	//批处理删除
	public boolean delData(String[] ids);

	//查询单条记录，以查看复选框选中的信息
	public Map<String, Object> viewData(String id);
}
