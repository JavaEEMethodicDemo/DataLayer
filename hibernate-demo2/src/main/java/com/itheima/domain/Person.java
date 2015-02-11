package com.itheima.domain;

import java.io.Serializable;
import java.util.Date;
/*
create database day31;
use day31;
create table PERSON(
	ID int primary key auto_increment,
	NAME varchar(100),
	BIRTHDAY date
);
 */
public class Person implements Serializable {
	private Integer id;//尽量使用包装类型   OID  类中与数据库主键对应的那个属性。Hibernate用它在内存中唯一确定给一个对象
	private String name;
	private Date birthday;
	private String gender;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birthday=" + birthday
				+ "]";
	}
	
	
}
