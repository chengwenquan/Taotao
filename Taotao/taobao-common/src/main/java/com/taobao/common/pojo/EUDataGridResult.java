package com.taobao.common.pojo;

import java.util.List;

/**
 * 为EasyUI提供数据
 * @author Administrator
 *
 */
public class EUDataGridResult {
	
	private long total;
	private List<?> rows; //<?>:表示可以是任意类型的数据
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
	

}
