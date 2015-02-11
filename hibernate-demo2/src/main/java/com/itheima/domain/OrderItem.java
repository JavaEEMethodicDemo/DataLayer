package com.itheima.domain;

import java.io.Serializable;

public class OrderItem implements Serializable {
	private Integer id;
	private Integer num;//数量
	private Float price;//单价
	private Float totalPrice;//小计=单价*数量。此属性没有必要存在数据库中
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", num=" + num + ", price=" + price
				+ ", totalPrice=" + totalPrice + "]";
	}
	
}
