package com.taobao.pojo;

import java.util.Date;
/**
 * 商品规则参数
 * @author Administrator
 *
 */
public class TbItemParam {
    private Long id;

    private Long itemCatId;//商品类目ID

    private Date created;

    private Date updated;

    private String paramData;//参数数据，格式为json格式
    
    
    //-------后期添加的属性-------------------------------
    private String itemCatName;//商品类目名称
   
    public String getItemCatName() {
		return itemCatName;
	}

	public void setItemCatName(String itemCatName) {
		this.itemCatName = itemCatName;
	}

	//-------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemCatId() {
        return itemCatId;
    }

    public void setItemCatId(Long itemCatId) {
        this.itemCatId = itemCatId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData == null ? null : paramData.trim();
    }
}