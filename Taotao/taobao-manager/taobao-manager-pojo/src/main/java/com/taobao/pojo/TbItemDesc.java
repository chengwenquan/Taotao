package com.taobao.pojo;

import java.util.Date;
/**
 * 商品描述表
 * @author Administrator
 *
 */
public class TbItemDesc {
    private Long itemId;//商品ID

    private String itemDesc;//商品描述
    
    private Date created;//创建时间

    private Date updated;//更新时间

    

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }
}