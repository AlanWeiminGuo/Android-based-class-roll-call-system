package com.bs.course.entity;

import java.util.List;

/**
 * 课程实体�?
 */
public class Course {
	private String id;//课程ID
	private String courseName;//课程名称
	private String state;//课程状态
	private String teacherId;//老师ID
	private String kcdescribe;//课程描述
	private Teacher teacher;//老师资料
    private List<Student> students;//学生列表
    
	public String getKcdescribe() {
		return kcdescribe;
	}
	public void setKcdescribe(String kcdescribe) {
		this.kcdescribe = kcdescribe;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}

}
