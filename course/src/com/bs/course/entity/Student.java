package com.bs.course.entity;

/**
 *学生对象实体�?
 */
public class Student {
	private String id;//学生ID
	private String studentId;//学号
	private String name;//姓名
	private String sex;//性别
	private String phone;//手机�?
	private String head;//头像
	private String password;//密码
	private String state;//上课状�??
	private String studentcj;//学生成绩
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getStudentcj() {
		return studentcj;
	}
	public void setStudentcj(String studentcj) {
		this.studentcj = studentcj;
	}
	
}
