package com.itheima.domain;

import java.io.Serializable;

public class OrderItem implements Serializable {
	private Integer id;
	private Integer num;//����
	private Float price;//����
	private Float totalPrice;//С��=����*������������û�б�Ҫ�������ݿ���
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
