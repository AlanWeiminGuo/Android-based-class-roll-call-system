package com.bs.course.dao;

import java.util.List;
import java.util.Map;

import com.bs.course.entity.ChengJi;
import com.bs.course.entity.Course;
import com.bs.course.entity.CourseSt;

public interface TeacherMapper {
	
	int addcourse(Course entity);
	
    List<Course> course_list(Map<String,Object> params);
    
    List<Course> course_list1(Map<String,Object> params);
    
    int delacourse(Course entity);
    
    int addstudent(CourseSt eCourseSt);
    
    int tquekecishu(Map<String,Object> params);
    
    int quekelvcount(Map<String,Object> params);
    
    int quekelvunsign(Map<String,Object> params);
    
    int quekelvall(Map<String,Object> params);
    
    String tquekelv(Map<String,Object> params);
    
    int qiandaochange(Map<String,Object> params);
    
    CourseSt getCoursest(Map<String,Object> params);
    
    List<CourseSt> course_stlist(Map<String,Object> params);
    
    List<CourseSt> course_stlist1(Map<String,Object> params);
    
    int updatsks(Map<String,Object> params);
    
    int qd(Map<String,Object> params);
    
    Course getCourse(Map<String,Object> params);
	
	int addcj(ChengJi chengJi);
	
	ChengJi getcj(Map<String,Object> params);
	
    List<ChengJi> st_cjlist(Map<String,Object> params);
    
    List<ChengJi> st_cjlist1(Map<String,Object> params);


   
}
