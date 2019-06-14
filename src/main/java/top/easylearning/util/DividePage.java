package top.easylearning.util;

public class DividePage {
	
	private int pageSize;//每一页显示的条数
	private int recordCount;//表示记录的总条数
	private int currentPage;//表示当前页
	
	public DividePage(int pageSize,int recordCount,int currentPage) {
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		setCurrentPage(currentPage);
		
	}
	
	public DividePage(int pageSize,int recordCount) {
		this(pageSize, recordCount, 1);
		
	}
	
	//获得总页数--size;
	public int getPageCount(){
		int size = recordCount/pageSize;//总页数 
		int mod = recordCount%pageSize;//最后一页的条数
		if (mod!=0) {
			size++;
		}
		return recordCount == 0?1:size;
	}
	
	//取出包含的记录条数，开始记录条数
	public int getFromIndex(){
		return (currentPage - 1)*pageSize;
	}
	
	public int getToIndex(){
		return pageSize;
	}
	
	//返回当前页
	public int getCurrentPage(){
		return currentPage;
	}
	//设置当前页
	public void setCurrentPage(int currentPage){
		//如果当前页小于等于0，则返回第一页，否则显示当前页
		int validPage = currentPage<=0?1:currentPage;
		//如果当前页大于当前页的时候则显示总页数，否则则显示当前页
		validPage = validPage>getPageCount()?getPageCount():validPage;
		this.currentPage = validPage;
	}
	
	public int getPageSize(){
		return pageSize;
	}
	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

}
