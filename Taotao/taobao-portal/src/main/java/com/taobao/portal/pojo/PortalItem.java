package com.taobao.portal.pojo;

import com.taobao.pojo.TbItem;

public class PortalItem extends TbItem {

	/**
	 * 子类继承父类,jsp获取images属性的时候会调用该方法
	 * @return
	 */
	public String[] getImages(){
		String images=this.getImage();
		if(!images.equals("")&&images!=null){
			String imgs[] = images.split(",");
			return imgs;
		}
		return null;
	}
}
