package com.peng.vo;

import java.math.BigDecimal;
import java.util.List;


//视图层的炫染
//为什么使用泛型 可以避免一些 类型转换的异常
public class AuctionPageVo<T> {
	//数据 因为炫染一个页面就要数据
	private List<T> lists; 
	//页的分页两个原因：
	//1.为了提高用户的交互
	//2.更重要的是提高性能，减少数据库的开销
	//页的索引
	private BigDecimal pageIndex;
	//页的条数
	private BigDecimal pageNum;
	//总条数
	private BigDecimal total;
	//尾页
	private BigDecimal endPage;
	
	public List<T> getLists() {
		return lists;
	}
	public void setLists(List<T> lists) {
		this.lists = lists;
	}
	public BigDecimal getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(BigDecimal pageIndex) {
		this.pageIndex = pageIndex;
	}
	public BigDecimal getPageNum() {
		return pageNum;
	}
	public void setPageNum(BigDecimal pageNum) {
		this.pageNum = pageNum;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getEndPage() {
		return endPage;
	}
	public void setEndPage(BigDecimal endPage) {
		this.endPage = endPage;
	}
	
	
	
	
	
	
}
