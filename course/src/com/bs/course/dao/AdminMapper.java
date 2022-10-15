package com.bs.course.dao;

import java.util.List;
import java.util.Map;

import com.bs.course.entity.Student;
import com.bs.course.entity.Teacher;


/**
 *管理员数据库操作 
 * @author Administrator
 */
public interface AdminMapper {
	
    List<Student> student_list(Map<String,Object> params);
    
    List<Student> student_list1(Map<String,Object> params);
	
	int delastudent (Student entity);
	
	Student getStudent(Map<String,Object> params);
	
	int saveStudent(Student entity);
	
	int upinfo(Student entity);
	
	int uppasswrod(Map<String,Object> params);
	
	int stquekecishu(Map<String,Object> params);
	
	int addteacher(Teacher entity);
	
	int tupinfo(Teacher entity);
	
	int tregister(Map<String,Object> params);
	
	int stregister(Map<String,Object> params);
	
	int uppasswrod1(Map<String,Object> params);
	
	Teacher getTeacher(Map<String,Object> params);
	
	List<Teacher> teacher_list(Map<String,Object> params);
	    
	List<Teacher> teacher_list1(Map<String,Object> params);
	
	int delateacher (Teacher entity);


}
