package com.itheima.domain;

import java.io.Serializable;

//���裺û��������ѧ��
public class Student implements Serializable{
	private String firstName;//Primary key
	private String secondName;//Primary key
	private String gender;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
