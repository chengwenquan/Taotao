package com.taobao.common.pojo;

import java.util.List;

public class ItemPara {
	
	private String group;
	private List<String> params;
	
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public List<String> getParams() {
		return params;
	}
	public void setParams(List<String> params) {
		this.params = params;
	}
	@Override
	public String toString() {
		return "ItemPara [group=" + group + ", params=" + params + "]";
	}

	
	
	
	

}
