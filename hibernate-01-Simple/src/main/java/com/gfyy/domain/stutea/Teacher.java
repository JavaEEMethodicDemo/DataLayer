package com.gfyy.domain.stutea;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 
 */
public class Teacher implements Serializable {
	private Integer id;
	private String name;
	private Float salary;
	private Set<Student> students = new HashSet<Student>();
	
	
	public Teacher() {
	}
	
	public Teacher(String name, Float salary) {
		this.name = name;
		this.salary = salary;
	}
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
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", salary=" + salary
				+ "]";
	}
	
	
	
}
