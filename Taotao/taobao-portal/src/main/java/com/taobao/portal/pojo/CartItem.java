package com.taobao.portal.pojo;
/**
 * 用于描述购物车中的商品
 * <p>Title:CartItem.java</p>
 * @author 承文全
 * @date 2018年12月21日
 * @version 1.0
 */
public class CartItem {

	private Long id;//商品id，同时也是商品编号
	
    private String title;//商品标题

    private Long price;//商品价格，单位为：分

    private Integer num;//购买数量

    private String image;//商品图片

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
    
    

}
